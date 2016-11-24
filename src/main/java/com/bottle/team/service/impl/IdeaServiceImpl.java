package com.bottle.team.service.impl;

import com.bottle.team.model.ideas.Idea;
import com.bottle.team.repository.IdeaRepository;
import com.bottle.team.service.IdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by PC on 09/10/2016.
 */
@Service
public class IdeaServiceImpl implements IdeaService {
    @Autowired
    IdeaRepository ideaRepository;

    public Iterable<Idea> findAll() {
        return ideaRepository.findAll();
    }

    public Idea findByTitle(String title) {
        return ideaRepository.findByTitle(title);
    }

    @Override
    public Idea save(Idea idea) {
        if (idea.getId() == null)
            idea.setCreationDate(new Date());
        idea.setLastModified(new Date());
        return ideaRepository.save(idea);
    }

    @Override
    public void delete(Long id) {
        ideaRepository.delete(id);
    }

    @Override
    public Idea findById(Long id) {
        return ideaRepository.findOne(id);
    }

    @Override
    public Idea add(Idea object) {
        return save(object);
    }
}
