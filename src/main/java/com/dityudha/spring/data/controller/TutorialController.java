package com.dityudha.spring.data.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dityudha.spring.data.model.Tutorial;
import com.dityudha.spring.data.repository.TutorialRepository;


//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TutorialController {
	  @Autowired
	  TutorialRepository tutorialRepository;
	  @GetMapping("/bikes")
	  public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String brand) {
	    try {
	      List<Tutorial> bikes = new ArrayList<Tutorial>();
	      if (brand == null)
	        tutorialRepository.findAll().forEach(bikes::add);
	      else
	        tutorialRepository.findByBrandContaining(brand).forEach(bikes::add);
	      if (bikes.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(bikes, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  @GetMapping("/bikes/{id}")
	  public ResponseEntity<Tutorial> getBikesById(@PathVariable("id") long id) {
	    Optional<Tutorial> tutorialData = tutorialRepository.findById(id);
	    if (tutorialData.isPresent()) {
	      return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  @PostMapping("/bikes")
	  public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
	    try {
	      Tutorial _tutorial = tutorialRepository
	          .save(new Tutorial(tutorial.getBrand(), tutorial.getType(), false));
	      return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  @PutMapping("/bikes/{id}")
	  public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
	    Optional<Tutorial> tutorialData = tutorialRepository.findById(id);
	    if (tutorialData.isPresent()) {
	      Tutorial _tutorial = tutorialData.get();
	      _tutorial.setBrand(tutorial.getBrand());
	      _tutorial.setType(tutorial.getType());
	      _tutorial.setSold(tutorial.isSold());
	      return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  @DeleteMapping("/bikes/{id}")
	  public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
	    try {
	      tutorialRepository.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  @DeleteMapping("/bikes")
	  public ResponseEntity<HttpStatus> deleteAllTutorials() {
	    try {
	      tutorialRepository.deleteAll();
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  @GetMapping("/bikes/sold")
	  public ResponseEntity<List<Tutorial>> findBySold() {
	    try {
	      List<Tutorial> tutorials = tutorialRepository.findBySold(true);
	      if (tutorials.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(tutorials, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
}