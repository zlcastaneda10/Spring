package com.springchallenge.firstspringapp.repositories;

import com.springchallenge.firstspringapp.models.Animes;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnimesRepository extends MongoRepository<Animes,String> {
}
