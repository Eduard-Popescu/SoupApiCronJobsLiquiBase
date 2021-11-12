package com.example.demo.service.implementation;

import com.example.demo.domain.Person;
import com.example.demo.domain.dto.input.SavePerson;
import com.example.demo.domain.dto.output.ExtractPerson;
import com.example.demo.mapper.PersonMapper;
import com.example.demo.repository.BalanceRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.api.PersonService;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PersonServiceImpl implements PersonService {

  @Value("classpath:schema.graphqls")
  private Resource schemaResource;

  private GraphQL graphQL;

  private final PersonRepository personRepository;

  @Autowired
  public PersonServiceImpl(PersonRepository personRepository, BalanceRepository balanceRepository){
        this.personRepository = personRepository;
  }

  @PostConstruct
  public void initSchema() throws IOException {
      File schema = schemaResource.getFile();
      TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schema);
      RuntimeWiring runtimeWiring = buildWiring();
      GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeRegistry, runtimeWiring);
      graphQL = GraphQL.newGraphQL(graphQLSchema).build();
  }

  private RuntimeWiring buildWiring() {
      DataFetcher<List<ExtractPerson>> fetcher1 = data ->{
            return getAllPersons();
      };

      DataFetcher<ExtractPerson> fetcher2 = data ->{
            return getPersonById(Long.parseLong(data.getArgument("personId")));
      };

      return RuntimeWiring.newRuntimeWiring().type("Query", typeWriting ->
            typeWriting.dataFetcher("getAllPersons", fetcher1).dataFetcher("getPersonById",fetcher2))
            .build();
  }

  public List<ExtractPerson> getAllPersons(){
      return personRepository.findAll().stream().map(PersonMapper::PersonToExtractPerson).collect(Collectors.toList());
  }

   public ExtractPerson getPersonById(Long personId){
      Person person = personRepository.findById(personId).orElseThrow(
           () -> new EntityNotFoundException("Person with specified id was not found."));

        return PersonMapper.PersonToExtractPerson(person);
   }

   @Transactional
   public void savePerson(SavePerson person){
      this.personRepository.save(PersonMapper.SavePersonToPerson(person));
   }

   public GraphQL getGraphQL(){
      return this.graphQL;
   }

}
