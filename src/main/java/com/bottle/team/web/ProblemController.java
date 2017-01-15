package com.bottle.team.web;

import com.bottle.team.model.ideas.Idea;
import com.bottle.team.model.ideas.Problem;
import com.bottle.team.model.interfaces.BaseEntity;
import com.bottle.team.service.ProblemService;
import com.bottle.team.service.helper.ProblemFilter;
import com.bottle.team.validation.ProblemValidator;
import com.bottle.team.web.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public Iterable<? extends BaseEntity> findAll(
            @RequestParam(required = false) Long questionerId,
            @RequestParam(required = false) Integer offset,
            @RequestParam(required = false) Integer limit,
            @RequestParam(required = false) String query) {
        ProblemFilter problemFilter = new ProblemFilter(null, questionerId);
        return problemService.findAll(query, offset, limit, problemFilter);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Problem findById(@PathVariable Long id) {
        Problem problem = problemService.findById(id);
        if (problem == null)
        throw new ResourceNotFoundException();
        return problem;
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
