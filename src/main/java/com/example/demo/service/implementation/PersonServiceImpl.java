package com.example.demo.service.implementation;

import com.example.demo.domain.Balance;
import com.example.demo.domain.Person;
import com.example.demo.domain.dto.input.SavePerson;
import com.example.demo.domain.dto.output.ExtractPerson;
import com.example.demo.mapper.PersonMapper;
import com.example.demo.repository.BalanceRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.api.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Service
public class PersonServiceImpl implements PersonService {

  private final PersonRepository personRepository;
  private final BalanceRepository balanceRepository;
  @Autowired
  public PersonServiceImpl(PersonRepository personRepository, BalanceRepository balanceRepository){
      this.personRepository = personRepository;
      this.balanceRepository = balanceRepository;
  }


   public ExtractPerson getPersonById(Long id){
      Person person = personRepository.findById(id).orElseThrow(
           () -> new EntityNotFoundException("Person with specified id was not found."));

        return PersonMapper.PersonToExtractPerson(person);
   }

   @Transactional
   public void savePerson(SavePerson person){
      this.personRepository.save(PersonMapper.SavePersonToPerson(person));
   }

    /**
     * @Scheduled(cron = "0/15 0 0 * * *")
     * daily at 12 : 00 AM
     */
   @Scheduled(cron = "0/15 * * * * *")
    public void fetchDBJob(){
       DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
       LocalDateTime now = LocalDateTime.now();
       AtomicReference<Float> newAmount = new AtomicReference<>(0.0f);
       List<Person> persons = personRepository.findAll();
       persons.forEach(person -> newAmount.updateAndGet(v -> v + person.getSold()));
       balanceRepository.save(Balance.builder().amount(newAmount.get()).status(dtf.format(now)).build());
       log.info("newBalance- Balance = {}",Balance.builder().amount(newAmount.get()).status(dtf.format(now)).build());
   }

}
