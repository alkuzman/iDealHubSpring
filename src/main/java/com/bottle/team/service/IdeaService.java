package com.bottle.team.service;

import com.bottle.team.model.ideas.Idea;

import java.util.List;

/**
 * Created by PC on 09/10/2016.
 */
public interface IdeaService extends Service<Idea> {

    Idea findByTitle(String title);

    Iterable<Idea> findByProblemId(Long id);
}
