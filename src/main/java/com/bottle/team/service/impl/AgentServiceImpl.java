package com.bottle.team.service.impl;

import com.bottle.team.auth.jwt.common.UserContext;
import com.bottle.team.model.authentication.Agent;
import com.bottle.team.model.interfaces.BaseEntity;
import com.bottle.team.service.AgentService;
import com.bottle.team.service.QueryService;
import com.bottle.team.service.helper.Filter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        Query finalQuery = null;
        Query filterQuery = filter.getQuery();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null)
            finalQuery = filterQuery;
        else {
            UserContext context = (UserContext) authentication.getPrincipal();
            String email = context.getUsername();
            if (filterQuery == null) {
                finalQuery = new TermQuery(new Term("email", email));
            } else {
                BooleanQuery.Builder builder = new BooleanQuery.Builder();
                builder.add(filterQuery, BooleanClause.Occur.MUST);
                builder.add(new TermQuery(new Term("email", email)), BooleanClause.Occur.MUST);
                finalQuery = builder.build();
            }
        }
        return queryService.search(query, finalQuery, offset, limit, Agent.class, fields);
    }
}
