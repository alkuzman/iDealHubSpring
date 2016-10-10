package com.bottle.team.service;

import com.bottle.team.model.Person;

/**
 * Created by PC on 09/10/2016.
 */
public interface PersonService {
    Iterable<Person> findAll();

    Person save(Person person);

    void delete(Long id);

    Person findById(Long id);
}
