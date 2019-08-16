package com.springchallenge.firstspringapp.resource;

import com.springchallenge.firstspringapp.models.Animes;
import com.springchallenge.firstspringapp.repositories.AnimesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/anime")
public class AnimesResource {

    @Autowired
    private AnimesRepository animesRepository;

    @GetMapping("/")
    @ResponseBody
    public Stream<Integer> getAll(@RequestParam(required = false) Integer limit, String genre) {
        Stream <Animes> animesStream = animesRepository.findAll().stream();
        Stream<Integer> animesList =null;
        if (genre != null){
            animesList = animesList.filter(Animes::getGenre.equals(genre) );
        }
        Stream<Integer> animesList = animesStream
                .map(Animes::getAnime_id);
        if ( limit != -1){
            animesList = animesList.limit(limit);
        }



        return animesList;
    }

    @GetMapping("/{anime_id}")
    @ResponseBody
    public Animes getAnimeById(){
        return null;
    }

}
