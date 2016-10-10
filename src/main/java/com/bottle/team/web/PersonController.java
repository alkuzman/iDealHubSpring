package com.bottle.team.web;

import com.bottle.team.model.Idea;
import com.bottle.team.model.Person;
import com.bottle.team.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by PC on 10/10/2016.
 */
@RestController()
@RequestMapping(value = "/people")
public class PersonController {
    @Autowired
    PersonService personService;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Person> findAll() {
        return personService.findAll();
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Person findById(@PathVariable Long id) {
        return personService.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Person save(@RequestBody Person person) {
        return personService.save(person);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        personService.delete(id);
    }
}
