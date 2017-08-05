package com.bottle.team.service.impl;

import com.bottle.team.model.interfaces.BaseEntity;
import com.bottle.team.model.sharing.Searchable;
import com.bottle.team.neo4j.Neo4jUtils;
import com.bottle.team.repository.SearchableRepository;
import com.bottle.team.service.QueryService;
import com.bottle.team.service.SearchableService;
import com.bottle.team.service.helper.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by AKuzmanoski on 20/01/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 20/01/2017
 */
@Service
public class SearchableServiceImpl implements SearchableService {
    final
    private SearchableRepository searchableRepository;
    final
    private QueryService queryService;
    private String[] fields;

    @Autowired
    public SearchableServiceImpl(SearchableRepository searchableRepository, QueryService queryService) {
        this.searchableRepository = searchableRepository;
        this.queryService = queryService;
    }

    @PostConstruct
    public void init() {
        fields = new String[17];
        fields[0] = "pckg.shareable.owner.name";
        fields[1] = "pckg.shareable.owner.firstName";
        fields[2] = "pckg.shareable.owner.lastName";
        fields[3] = "pckg.shareable.owner.email";
        fields[4] = "pckg.shareable.text";
        fields[5] = "pckg.shareable.title";
        fields[6] = "pckg.shareable.snackPeak";
        fields[7] = "pckg.shareable.problem.title";
        fields[8] = "pckg.shareable.problem.text";
        fields[9] = "pckg.shareable.questioner.name";
        fields[10] = "pckg.shareable.questioner.firstName";
        fields[11] = "pckg.shareable.questioner.lastName";
        fields[12] = "pckg.shareable.questioner.email";
        fields[13] = "firstName";
        fields[14] = "lastName";
        fields[15] = "email";
        fields[16] = "name";
    }

    @Override
    public Iterable<Searchable> findAll() {
        return searchableRepository.findAll();
    }

    @Override
    public Searchable save(Searchable object) {
        return searchableRepository.save(object);
    }

    @Override
    public Searchable add(Searchable object) {
        return this.save(object);
    }

    @Override
    public void delete(Long id) {
        searchableRepository.deleteById(id);
    }

    @Override
    public Searchable findById(Long id) {
        return Neo4jUtils.findById(searchableRepository, id);
    }

    @Override
    public Iterable<? extends BaseEntity> findAll(String query, Integer offset, Integer limit, Filter filter) {
        return queryService.search(query, filter.getQuery(), offset, limit, Searchable.class, fields);
    }
}
