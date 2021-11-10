package com.example.demo.service.api;

import com.example.demo.domain.dto.input.SavePerson;
import com.example.demo.domain.dto.output.ExtractPerson;

public interface PersonService {

    ExtractPerson getPersonById(Long id);
    void savePerson(SavePerson person);
}
