package com.bottle.team.service;

import com.bottle.team.model.ideas.Problem;
import com.bottle.team.model.interfaces.BaseEntity;
import com.bottle.team.service.helper.IdeaFilter;
import com.bottle.team.service.helper.ProblemFilter;

/**
 * Created by Viki on 10/14/2016.
 */
public interface ProblemService extends Service<Problem> {
    Iterable<Problem> findByQuestionerId(Long questionerId);

    Iterable<? extends BaseEntity> findAll(String query, Integer offset, Integer limit, ProblemFilter filter);
}
