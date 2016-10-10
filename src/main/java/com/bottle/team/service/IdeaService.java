package com.bottle.team.service;

import com.bottle.team.model.Idea;

/**
 * Created by PC on 09/10/2016.
 */
public interface IdeaService {
    Iterable<Idea> findAll();

    Idea findByTitle(String title);

    Idea save(Idea idea);

    void delete(Long id);

    Idea findById(Long id);
}
