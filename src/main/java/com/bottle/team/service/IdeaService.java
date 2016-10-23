package com.bottle.team.service;

import com.bottle.team.model.ideas.Idea;

/**
 * Created by PC on 09/10/2016.
 */
public interface IdeaService extends Service<Idea> {

    Idea findByTitle(String title);
}
