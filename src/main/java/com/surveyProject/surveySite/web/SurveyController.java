package com.surveyProject.surveySite.web;

import com.surveyProject.surveySite.model.Survey;
import com.surveyProject.surveySite.model.SurveyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
class SurveyController {

    private final Logger log = LoggerFactory.getLogger(SurveyController.class);
    private SurveyRepository surveyRepository;

    public SurveyController(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    @GetMapping("/surveys")
    Collection<Survey> surveys() {
        return surveyRepository.findAll();
    }

    @GetMapping("/survey/{id}")
    ResponseEntity<?> getSurvey(@PathVariable Long id) {
        Optional<Survey> survey = surveyRepository.findById(id);
        return survey.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/survey")
    ResponseEntity<Survey> createSurvey(@Valid @RequestBody Survey survey) throws URISyntaxException {
        log.info("Request to create survey: {}", survey);
        Survey result = surveyRepository.save(survey);
        return ResponseEntity.created(new URI("/api/survey/" + result.getId()))
                .body(result);
    }

    @PutMapping("/survey/{id}")
    ResponseEntity<Survey> updateSurvey(@PathVariable Long id, @Valid @RequestBody Survey survey) {
    	survey.setId(id);
        log.info("Request to update survey: {}", survey);
        Survey result = surveyRepository.save(survey);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/survey/{id}")
    public ResponseEntity<?> deleteSurvey(@PathVariable Long id) {
        log.info("Request to delete survey: {}", id);
        surveyRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
