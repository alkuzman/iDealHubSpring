package com.bottle.team.service.impl;

import com.bottle.team.model.authentication.Agent;
import com.bottle.team.model.interfaces.BaseEntity;
import com.bottle.team.service.AgentService;
import com.bottle.team.service.QueryService;
import com.bottle.team.service.helper.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by Viki on 1/25/2017.
 */
@Service
public class AgentServiceImpl implements AgentService {
    @Autowired
    private QueryService queryService;
    private String[] fields;

    @PostConstruct
    public void init() {
        this.fields = new String[4];
        this.fields[0] = "name";
        this.fields[1] = "firstName";
        this.fields[2] = "lastName";
        this.fields[3] = "email";
    }

    @Override
    public Iterable<? extends BaseEntity> findAll(String query, Integer offset, Integer limit, Filter filter) {
        return queryService.search(query, filter.getQuery(), offset, limit, Agent.class, fields);
    }
}
