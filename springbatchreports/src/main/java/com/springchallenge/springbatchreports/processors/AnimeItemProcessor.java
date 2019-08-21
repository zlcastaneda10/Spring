package com.springchallenge.springbatchreports.processors;

import com.springchallenge.springbatchreports.models.AnimeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.stream.Stream;

@RestController
@RequestMapping("/start")
public class AnimeItemProcessor implements ItemProcessor<AnimeDTO, AnimeDTO> {

    private static final Logger LOG = LoggerFactory.getLogger(AnimeItemProcessor.class);
    private static final String URI  = "http://localhost:8085/anime";

    @Override
    public AnimeDTO process(AnimeDTO animeDTO) throws Exception {

        LOG.info("Recibiendo informacion del API REST");

        return null;
    }


    @GetMapping("/test")
    public void testAnime()
    {
        RestTemplate restTemplate = new RestTemplate();
        Stream<Integer> ids = Arrays.stream(restTemplate.getForObject("http://localhost:8085/anime/top?limit=10&genre=Romance", Integer[].class));
        //ids.forEach(s ->LOG.info(""+s));

        Stream<AnimeDTO> animes = Arrays.stream(restTemplate.getForObject("http://localhost:8085/anime/508", AnimeDTO[].class));
        ids.forEach( animesids -> {
            AnimeDTO anime = restTemplate.getForObject("http://localhost:8085/anime/"+animesids,AnimeDTO[].class)[0];
            LOG.info(anime.getName());
        });
    }

    @GetMapping("/topAnimeByGenre")
    public void topAnimeByGenre(
            @RequestParam Integer limit,
            @RequestParam String genre)
    {
        RestTemplate restTemplate = new RestTemplate();
        Stream<Integer> ids = Arrays.stream(restTemplate.getForObject(URI+"/top?limit="+limit+"&genre="+genre+"", Integer[].class));
        ids.forEach( animesids -> {
            AnimeDTO anime = restTemplate.getForObject(URI+animesids,AnimeDTO[].class)[0];
            LOG.info(anime.getName());
        });
    }

@GetMapping("/topAnimeByType")
    public void topAnimeByType(
            @RequestParam Integer limit,
            @RequestParam String type)
    {
        RestTemplate restTemplate = new RestTemplate();
        Stream<Integer> ids = Arrays.stream(restTemplate.getForObject(URI+"/top?limit="+limit+"&type="+type+"", Integer[].class));
        ids.forEach( animesids -> {
            AnimeDTO anime = restTemplate.getForObject(URI+animesids,AnimeDTO[].class)[0];
            LOG.info(anime.getName());
        });
    }

    @GetMapping("/topAnimeByStudio")
    public void topAnimeByType(
            @RequestParam Integer limit,
            @RequestParam String mainCast,
            @RequestParam String studio)
    {
        RestTemplate restTemplate = new RestTemplate();
        Stream<Integer> ids = Arrays.stream(restTemplate.getForObject(URI+"/top?limit="+limit+"&mainCast="+mainCast+"&studio="+studio+"", Integer[].class));
        ids.forEach( animesids -> {
            AnimeDTO anime = restTemplate.getForObject("http://localhost:8085/anime/"+animesids,AnimeDTO[].class)[0];
            LOG.info(anime.getName());
        });
    }



}
