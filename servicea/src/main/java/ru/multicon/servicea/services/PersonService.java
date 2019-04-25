package ru.multicon.servicea.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.multicon.servicea.entities.Person;
import ru.multicon.servicea.repositories.PersonRepository;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
@Transactional
public class PersonService {

    private PersonRepository personRepository;

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public List<Person> list() {
        return personRepository.findAll();
    }
}
