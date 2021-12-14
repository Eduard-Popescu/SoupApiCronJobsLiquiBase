package com.example.demo.service.implementation;


import com.example.demo.domain.dto.input.SavePerson;
import com.example.demo.domain.dto.output.ExtractPerson;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import graphql.GraphQL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles({"unit-testing"})
@TestExecutionListeners({
    DbUnitTestExecutionListener.class,
    DependencyInjectionTestExecutionListener.class
})
public class PersonServiceTest {

    @Autowired
    private PersonServiceImpl personService;
    private SavePerson newPerson;


    @BeforeEach
    public void setUp(){
      newPerson = SavePerson.builder()
          .name("New Person")
          .email("newperson@yahoo.com")
          .sold(230.0f)
          .address("newAddress")
          .build();
    }

    @Test
    @DatabaseSetup(value = "/dbunit/unitTestDB.xml")
    public void given_an_person_id_when_i_call_getPersonById_return_a_person_mapped_with_ExtractPerson(){
        ExtractPerson person = personService.getPersonById(1L);
        assertEquals("Popescu Eduard",person.getName());
        assertEquals(300.0f,person.getSold());
        assertEquals("popescueduard50@yahoo.com",person.getEmail());
    }


    @Test
    @DatabaseSetup(value = "/dbunit/unitTestDB.xml")
    public void when_i_call_getAllPersons_return_a_list_of_persons_mapped_with_ExtractPerson(){
        List<ExtractPerson> persons = personService.getAllPersons();
        assertNotNull(persons);
        assertNotEquals(0,persons.size());
        assertEquals(3,persons.size());
    }


    @Test
    @DatabaseSetup(value = "/dbunit/unitTestDB.xml")
    public void given_an_person_mapped_with_SavePerson_when_i_call_savePerson_a_new_person_is_added_in_db(){
        personService.savePerson(newPerson);
        List<ExtractPerson> persons = personService.getAllPersons();
        assertEquals(4,persons.size());
        assertEquals("New Person",personService.getPersonById(4L).getName());
        assertEquals("newperson@yahoo.com",personService.getPersonById(4L).getEmail());
        assertEquals(230.0f,personService.getPersonById(4L).getSold());
    }

    @Test
    public void when_i_call_getGraphQL_return_current_graphQl_object(){
        GraphQL graphQL = personService.getGraphQL();
        assertNotNull(graphQL);
    }
}
