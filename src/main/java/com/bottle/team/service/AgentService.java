package com.bottle.team.service;

import com.bottle.team.model.interfaces.BaseEntity;
import com.bottle.team.service.helper.Filter;

/**
 * Created by Viki on 1/25/2017.
 */
public interface AgentService {
    Iterable<? extends BaseEntity> findAll(String query, Integer offset, Integer limit, Filter filter);
}
