package com.bottle.team.web;

import com.bottle.team.model.authentication.User;
import com.bottle.team.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by PC on 10/10/2016.
 */
@RestController()
@RequestMapping(value = "/people")
public class UserController {
    @Autowired
    UserService personService;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<User> findAll() {
        return personService.findAll();
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public User findById(@PathVariable Long id) {
        return personService.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public User save(@RequestBody User user) {
        return personService.save(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        personService.delete(id);
    }

}
