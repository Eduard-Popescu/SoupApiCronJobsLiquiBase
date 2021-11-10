package com.example.demo.mapper;

import com.example.demo.domain.Person;
import com.example.demo.domain.dto.input.SavePerson;
import com.example.demo.domain.dto.output.ExtractPerson;

public class PersonMapper {

    public static Person SavePersonToPerson (SavePerson pers){
    return Person.builder().name(pers.getName()).sold(pers.getSold()).build();
    }

    public static ExtractPerson PersonToExtractPerson (Person person){
    return ExtractPerson.builder()
        .personId(person.getId())
        .name(person.getName())
        .sold(person.getSold())
        .build();
    }
}
