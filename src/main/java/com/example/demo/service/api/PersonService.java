package com.example.demo.service.api;

import com.example.demo.domain.dto.input.SavePerson;
import com.example.demo.domain.dto.output.ExtractPerson;
import graphql.GraphQL;

import java.util.List;

public interface PersonService {

    List<ExtractPerson> getAllPersons();
    ExtractPerson getPersonById(Long id);
    void savePerson(SavePerson person);
    GraphQL getGraphQL();
}
