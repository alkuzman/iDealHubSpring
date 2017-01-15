package com.bottle.team.service;

import com.bottle.team.model.ideas.Solution;
import com.bottle.team.model.interfaces.BaseEntity;
import com.bottle.team.service.helper.IdeaFilter;
import com.bottle.team.service.helper.SolutionFilter;

/**
 * Created by Viki on 10/15/2016.
 */
public interface SolutionService extends Service<Solution> {
    Iterable<? extends BaseEntity> findAll(String query, Integer offset, Integer limit, SolutionFilter filter);
}
