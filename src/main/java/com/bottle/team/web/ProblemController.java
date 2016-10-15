package com.bottle.team.web;

import com.bottle.team.model.Person;
import com.bottle.team.model.Problem;
import com.bottle.team.model.interfaces.Questioner;
import com.bottle.team.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Viki on 10/14/2016.
 */
@RestController
@RequestMapping(value = "/problems")
public class ProblemController {
    @Autowired
    ProblemService problemService;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Problem> findAll() {
        return problemService.findAll();
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Problem findById(@PathVariable Long id) {
        return problemService.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Problem save(@RequestBody Problem problem) {
        return problemService.save(problem);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        problemService.delete(id);
    }
}
