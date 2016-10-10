package com.bottle.team.web;

import com.bottle.team.model.Idea;
import com.bottle.team.service.IdeaService;
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

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Idea findById(@PathVariable Long id) {
        return ideaService.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Idea save(@RequestBody Idea idea) {
        return ideaService.save(idea);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        ideaService.delete(id);
    }
}
