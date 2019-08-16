package com.springchallenge.firstspringapp.config;

import com.springchallenge.firstspringapp.repositories.AnimesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses =  AnimesRepository.class)
@Configuration
public class MongoDBConfig {



}
