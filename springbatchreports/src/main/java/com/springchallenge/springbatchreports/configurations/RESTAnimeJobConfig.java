package com.springchallenge.springbatchreports.configurations;

import com.springchallenge.springbatchreports.models.AnimeDTO;
import com.springchallenge.springbatchreports.readers.RESTAnimeReader;
import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RESTAnimeJobConfig {
//    @Bean
//    ItemReader<AnimeDTO> restAnimeReader(
//            Environment environment,
//            RestTemplate restTemplate
//    ){
//        return new RESTAnimeReader(environment.getRequiredProperty("http://localhost:8085/anime/508"),
//                restTemplate);
//    }
}
