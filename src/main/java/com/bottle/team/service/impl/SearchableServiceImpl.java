package com.bottle.team.service.impl;

import com.bottle.team.model.interfaces.BaseEntity;
import com.bottle.team.model.sharing.Searchable;
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
        fields[0] = "pckg.sharable.owner.name";
        fields[1] = "pckg.sharable.owner.firstName";
        fields[2] = "pckg.sharable.owner.lastName";
        fields[3] = "pckg.sharable.owner.email";
        fields[4] = "pckg.sharable.text";
        fields[5] = "pckg.sharable.title";
        fields[6] = "pckg.sharable.snackPeak";
        fields[7] = "pckg.sharable.problem.title";
        fields[8] = "pckg.sharable.problem.text";
        fields[9] = "pckg.sharable.questioner.name";
        fields[10] = "pckg.sharable.questioner.firstName";
        fields[11] = "pckg.sharable.questioner.lastName";
        fields[12] = "pckg.sharable.questioner.email";
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
        searchableRepository.delete(id);
    }

    @Override
    public Searchable findById(Long id) {
        return searchableRepository.findOne(id);
    }

    @Override
    public Iterable<? extends BaseEntity> findAll(String query, Integer offset, Integer limit, Filter filter) {
        return queryService.search(query, filter.getQuery(), offset, limit, Searchable.class, fields);
    }
}
