package com.bottle.team.service.impl;

import com.bottle.team.model.Person;
import com.bottle.team.repository.PersonRepository;
import com.bottle.team.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by PC on 09/10/2016.
 */
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public Iterable<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void delete(Long id) {
        personRepository.delete(id);
    }

    @Override
    public Person findById(Long id) {
        return personRepository.findOne(id);
    }
}
