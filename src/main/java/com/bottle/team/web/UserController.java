package com.bottle.team.web;

import com.bottle.team.model.authentication.User;
import com.bottle.team.service.UserService;
import com.bottle.team.validation.UserRegistrationValidator;
import com.bottle.team.web.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by PC on 10/10/2016.
 */
@RestController()
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserRegistrationValidator registrationValidator;

    @InitBinder("user")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(registrationValidator);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<User> findAll(Principal principal) {
        return userService.findAll();
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public User findById(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user == null)
            throw new ResourceNotFoundException();
        return user;
    }

    @RequestMapping(method = RequestMethod.POST)
    public User save(@Valid @RequestBody User user) {
        return userService.add(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @RequestMapping(method = RequestMethod.GET, params = "email")
    public User getUserByEmail(@RequestParam String email) {
        User user = userService.findByEmail(email);
        if (user == null) {
            throw new ResourceNotFoundException();
        }
        return user;
    }

    @RequestMapping(value = "/activation", method = RequestMethod.GET, params = "code")
    public User activate(@RequestParam String code) {
        User user = userService.activate(code);
        return user;
    }

    @RequestMapping(value = "/activationCode", method = RequestMethod.GET, params = "email")
    public void resendActivationCode(@RequestParam String email) {
        userService.resendActivationCode(email);
        return ;
    }
}
