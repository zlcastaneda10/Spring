package com.springchallenge.springbatchreports.readers;

import com.springchallenge.springbatchreports.models.AnimeDTO;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


public class RESTAnimeReader implements ItemReader<AnimeDTO> {
    private final String apiUrl;
    private final RestTemplate restTemplate;
    private int nextAnimeIndex;
    private List<AnimeDTO> animesData;

    public RESTAnimeReader(String apiUrl, RestTemplate restTemplate) {
        this.apiUrl = apiUrl;
        this.restTemplate = restTemplate;
        this.nextAnimeIndex = 0;
    }

    @Override
    public AnimeDTO read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (animeDataIsNotInitialized()) {
            animesData = fetchStudentDataFromAPI();
        }

        AnimeDTO nextAnime = null;

        if (nextAnimeIndex < animesData.size()) {
            nextAnime = animesData.get(nextAnimeIndex);
            nextAnimeIndex++;
        }

        return nextAnime;
    }

    private boolean animeDataIsNotInitialized() {
        return this.animesData == null;
    }

    private List<AnimeDTO> fetchStudentDataFromAPI() {
        ResponseEntity<AnimeDTO[]> response = restTemplate.getForEntity(
                apiUrl,
                AnimeDTO[].class
        );
        AnimeDTO[] studentData = response.getBody();
        return Arrays.asList(studentData);
    }

}
