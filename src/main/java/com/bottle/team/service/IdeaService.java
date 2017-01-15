package com.bottle.team.service;

import com.bottle.team.model.ideas.Idea;
import com.bottle.team.model.interfaces.BaseEntity;
import com.bottle.team.service.helper.IdeaFilter;

import java.util.List;

/**
 * Created by PC on 09/10/2016.
 */
public interface IdeaService extends Service<Idea> {

    Idea findByTitle(String title);

    Iterable<Idea> findByProblemId(Long id);

    Iterable<Idea> findByOwnerId(Long ownerId);

    Iterable<? extends BaseEntity> findAll(String query, Integer offset, Integer limit, IdeaFilter filter);
}
