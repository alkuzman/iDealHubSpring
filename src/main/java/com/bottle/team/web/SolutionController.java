package com.bottle.team.web;

import com.bottle.team.model.ideas.Solution;
import com.bottle.team.model.interfaces.BaseEntity;
import com.bottle.team.service.SolutionService;
import com.bottle.team.service.helper.SolutionFilter;
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
    private final
    SolutionService solutionService;
    private final
    SolutionValidator solutionValidator;

    @Autowired
    public SolutionController(SolutionService solutionService, SolutionValidator solutionValidator) {
        this.solutionService = solutionService;
        this.solutionValidator = solutionValidator;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<? extends BaseEntity> findAll(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) Integer offset,
            @RequestParam(required = false) Integer limit,
            @RequestParam(required = false) Long ideaId,
            @RequestParam(required = false) Long problemId,
            @RequestParam(required = false) Long ideaOwnerId,
            @RequestParam(required = false) Long problemQuestionerId
    ) {
        SolutionFilter solutionFilter = new SolutionFilter(null, ideaOwnerId, problemQuestionerId, ideaId, problemId);
        return solutionService.findAll(query, offset, limit, solutionFilter);
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
