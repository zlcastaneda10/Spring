package com.springchallenge.firstspringapp.controller;

import com.springchallenge.firstspringapp.models.Animes;
import com.springchallenge.firstspringapp.repositories.AnimesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.stream.Stream;

@RestController
@RequestMapping("/anime")
public class AnimesController {
    private static final Logger LOG = LoggerFactory.getLogger(AnimesController.class);

    @Autowired
    private AnimesRepository animesRepository;

    @GetMapping("/")
    @ResponseBody
    public Stream<Integer> getAll(
            @RequestParam(required = false, defaultValue = "-1") Integer limit,
            @RequestParam(required = false) String genre
    ) {
        Stream <Animes> animesStream = animesRepository.findAll().stream();

        if (genre != null){
            animesStream = animesStream.filter(i -> i.getGenre().stream().anyMatch(s -> s.equalsIgnoreCase(genre)));
        }

        if ( limit != -1){
            animesStream = animesStream.limit(limit);
        }
        //TODO Refactor To Int
        Stream<Integer> idList = animesStream
                .map(Animes::getAnime_id);
        return idList;
    }

    @GetMapping("/{anime_id}")
    @ResponseBody
    public Stream<Animes> getAnimeById(@PathVariable("anime_id") Integer anime_id )
    {

        Stream<Animes> animesStream = animesRepository.findAll()
                .stream()
                .filter(anime -> anime.getAnime_id() == anime_id);
        return animesStream;
    }

    @GetMapping("/top")
    @ResponseBody
    public Stream<Integer> getTopAnimes(
            @RequestParam(required = false, defaultValue = "100" ) Integer limit,
            @RequestParam (required = false) String genre,
            @RequestParam (required = false) String type,
            @RequestParam (required = false)String studio,
            @RequestParam (required = false) String source,
            @RequestParam (required = false) String mainCast
    )
    {

        Sort sort = new Sort(Sort.Direction.DESC, "rating");
        Stream<Animes> animesStream = animesRepository.findAll(sort).stream();



        if (genre != null){
            animesStream = animesStream.filter(i -> i.getGenre().stream().anyMatch(s -> s.equalsIgnoreCase(genre)));
        }

        if (type != null){
            //kinda assuming but if a studio exists in the list we must return?
            animesStream = animesStream.filter(e -> e.getType().equalsIgnoreCase(type));
        }

        if ( studio != null){
            animesStream = animesStream.filter(e-> e.getStudios().stream().anyMatch(s -> s.equalsIgnoreCase(studio)));
        }

        if (source != null){
            animesStream = animesStream.filter( e -> e.getSource().equalsIgnoreCase(source));
        }

        if (mainCast != null){
            animesStream = animesStream.filter(e -> e.getMain_cast().stream().anyMatch( s -> s.equalsIgnoreCase(mainCast)));
        }

        animesStream = animesStream.limit(limit);

        Stream<Integer> idList = animesStream
                .map(Animes::getAnime_id);
        return idList;

    }

    @GetMapping("/test")
    public void testAnime()
    {
        RestTemplate restTemplate = new RestTemplate();
        Stream<Integer> ids = Arrays.stream(restTemplate.getForObject("http://localhost:8085/anime/top?limit=10&genre=Romance", Integer[].class));
        //ids.forEach(s ->LOG.info(""+s));

        Stream<Animes> animes = Arrays.stream(restTemplate.getForObject("http://localhost:8085/anime/508", Animes[].class));
        ids.forEach( animesids -> {
            Animes anime = restTemplate.getForObject("http://localhost:8085/anime/"+animesids,Animes[].class)[0];
            LOG.info(anime.name);
        });
    }





}
