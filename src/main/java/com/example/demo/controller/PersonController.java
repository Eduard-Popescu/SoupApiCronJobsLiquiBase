package com.example.demo.controller;


import com.example.demo.domain.dto.input.SavePerson;
import com.example.demo.domain.dto.output.ExtractPerson;
import com.example.demo.service.api.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("person")
public class PersonController {

    private final PersonService personService;

    public PersonController (PersonService personService){
        this.personService = personService;
    }


    @GetMapping
    public ResponseEntity<ExtractPerson> getPerson (@RequestParam Long personId){
        return ResponseEntity.ok(personService.getPersonById(personId));
    }

    @PostMapping
    public ResponseEntity<String> savePerson(@RequestBody SavePerson person){
        personService.savePerson(person);
        return new ResponseEntity<>("The person was successfully saved", HttpStatus.ACCEPTED);
    }
}
