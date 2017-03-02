package com.bottle.team.service.impl;

import com.bottle.team.model.ideas.Idea;
import com.bottle.team.model.interfaces.BaseEntity;
import com.bottle.team.repository.IdeaRepository;
import com.bottle.team.service.IdeaService;
import com.bottle.team.service.QueryService;
import com.bottle.team.service.helper.IdeaFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by PC on 09/10/2016.
 */
@Service
public class IdeaServiceImpl implements IdeaService {
    final
    private QueryService queryService;
    final
    private IdeaRepository ideaRepository;
    private String[] fields;

    @Autowired
    public IdeaServiceImpl(QueryService queryService, IdeaRepository ideaRepository) {
        this.queryService = queryService;
        this.ideaRepository = ideaRepository;
    }

    @PostConstruct
    public void init() {
        this.fields = new String[7];
        this.fields[0] = "title";
        this.fields[1] = "snackPeak";
        this.fields[2] = "problem.title";
        this.fields[3] = "problem.text";
        this.fields[4] = "owner.firstName";
        this.fields[5] = "owner.lastName";
        this.fields[6] = "owner.email";
    }

    public Iterable<Idea> findAll() {
        return ideaRepository.findAll();
    }

    public Idea findByTitle(String title) {
        return ideaRepository.findByTitle(title);
    }

    @Override
    public Idea save(Idea idea) {
        return ideaRepository.save(idea);
    }

    @Override
    public void delete(Long id) {
        ideaRepository.delete(id);
    }

    @Override
    public Idea findById(Long id) {
        return ideaRepository.findOne(id);
    }

    @Override
    public Idea add(Idea object) {
        return save(object);
    }

    @Override
    public Iterable<Idea> findByProblemId(Long id) {
        return ideaRepository.findByProblem_Id(id);
    }

    @Override
    public Iterable<Idea> findByOwnerId(Long ownerId) {
        return ideaRepository.findByOwner_Id(ownerId);
    }

    @Override
    public Iterable<? extends BaseEntity> findAll(String query, Integer offset, Integer limit, IdeaFilter filter) {
        return queryService.search(query, filter.getQuery(), offset, limit, Idea.class, this.fields);
    }
}
