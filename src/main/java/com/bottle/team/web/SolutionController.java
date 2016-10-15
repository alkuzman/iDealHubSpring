package com.bottle.team.web;

import com.bottle.team.model.Problem;
import com.bottle.team.model.Solution;
import com.bottle.team.service.ProblemService;
import com.bottle.team.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Viki on 10/15/2016.
 */
@RestController
@RequestMapping(value = "/solutions")
public class SolutionController {
    @Autowired
    SolutionService solutionService;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Solution> findAll() {
        return solutionService.findAll();
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Solution findById(@PathVariable Long id) {
        return solutionService.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Solution save(@RequestBody Solution solution) {
        return solutionService.save(solution);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        solutionService.delete(id);
    }
}
