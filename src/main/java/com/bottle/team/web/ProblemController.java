package com.bottle.team.web;

import com.bottle.team.auth.jwt.common.UserContext;
import com.bottle.team.model.ideas.Problem;
import com.bottle.team.service.ProblemService;
import com.bottle.team.validation.ProblemValidator;
import com.bottle.team.validation.UserRegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by Viki on 10/14/2016.
 */
@RestController
@RequestMapping(value = "/problems")
public class ProblemController {
    @Autowired
    ProblemService problemService;

    @Autowired
    ProblemValidator problemValidator;

    @InitBinder("user")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(problemValidator);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Problem> findAll() {
        return problemService.findAll();
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Problem findById(@PathVariable Long id) {
        return problemService.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Problem save(@Valid @RequestBody Problem problem) {
        return problemService.save(problem);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        problemService.delete(id);
    }
}
