package com.example.demo.cronjobs;

import com.example.demo.domain.Balance;
import com.example.demo.domain.Person;
import com.example.demo.repository.BalanceRepository;
import com.example.demo.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Component
public class DailyUpdate {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    BalanceRepository balanceRepository;

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
