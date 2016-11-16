package com.bottle.team.web;

import com.bottle.team.model.authentication.User;
import com.bottle.team.service.UserService;
import com.bottle.team.web.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Created by PC on 10/10/2016.
 */
@RestController()
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    UserService personService;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<User> findAll(Principal principal) {
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

    @RequestMapping(method = RequestMethod.GET, params = "email")
    public User getUserByEmail(@RequestParam String email) {
        User user = personService.findByEmail(email);
        if (user == null) {
            throw new ResourceNotFoundException();
        }
        return user;
    }
}
