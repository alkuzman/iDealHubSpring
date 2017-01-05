package com.bottle.team.web;

import com.bottle.team.model.ideas.Solution;
import com.bottle.team.service.SolutionService;
import com.bottle.team.validation.SolutionValidator;
import com.bottle.team.web.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Viki on 10/15/2016.
 */
@RestController
@RequestMapping(value = "/solutions")
public class SolutionController {
    @Autowired
    SolutionService solutionService;
    @Autowired
    SolutionValidator solutionValidator;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Solution> findAll() {
        return solutionService.findAll();
    }

    @InitBinder("user")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(solutionValidator);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Solution findById(@PathVariable Long id) {
        Solution solution = solutionService.findById(id);
        if (solution == null)
            throw new ResourceNotFoundException();
        return solution;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Solution save(@Valid @RequestBody Solution solution) {
        return solutionService.save(solution);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        solutionService.delete(id);
    }
}
