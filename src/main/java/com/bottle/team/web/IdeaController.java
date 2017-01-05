package com.bottle.team.web;

import com.bottle.team.model.ideas.Idea;
import com.bottle.team.service.IdeaService;
import com.bottle.team.web.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by PC on 09/10/2016.
 */
@RestController()
@RequestMapping(value = "/ideas")
public class IdeaController {
    @Autowired
    IdeaService ideaService;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Idea> findAll() {
        return ideaService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, params = "problemId")
    public Iterable<Idea> ideaListByProblemId(@RequestParam Long problemId) {
        return ideaService.findByProblemId(problemId);
    }

    @RequestMapping(method = RequestMethod.GET, params = "ownerId")
    public Iterable<Idea> ideaListByUserId(@RequestParam Long ownerId) {
        return ideaService.findByOwnerId(ownerId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Idea findById(@PathVariable Long id) {
        Idea idea = ideaService.findById(id);
        if (idea == null)
            throw new ResourceNotFoundException();
        return idea;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Idea save(@RequestBody Idea idea) {
        System.out.println(idea);
        return ideaService.save(idea);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        ideaService.delete(id);
    }
}
