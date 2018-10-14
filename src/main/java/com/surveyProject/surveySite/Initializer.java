package com.surveyProject.surveySite;

import com.surveyProject.surveySite.model.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

@Component
class Initializer implements CommandLineRunner {

    private final SurveyRepository repository;

    public Initializer(SurveyRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) {
        Stream.of("First Survey", "My survey", "Test Survey",
                "Survey Survey").forEach(name ->
                repository.save(new Survey(name))
        );

        repository.findAll().forEach(System.out::println);
    }
}
