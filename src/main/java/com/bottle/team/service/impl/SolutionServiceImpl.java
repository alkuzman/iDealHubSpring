package com.bottle.team.service.impl;

import com.bottle.team.model.ideas.Solution;
import com.bottle.team.model.interfaces.BaseEntity;
import com.bottle.team.repository.SolutionRepository;
import com.bottle.team.service.QueryService;
import com.bottle.team.service.SolutionService;
import com.bottle.team.service.helper.IdeaFilter;
import com.bottle.team.service.helper.SolutionFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;

/**
 * Created by Viki on 10/15/2016.
 */
@Service
public class SolutionServiceImpl implements SolutionService {
    private final
    SolutionRepository solutionRepository;
    private final
    QueryService queryService;
    private String[] fields;

    @Autowired
    public SolutionServiceImpl(SolutionRepository solutionRepository, QueryService queryService) {
        this.solutionRepository = solutionRepository;
        this.queryService = queryService;
    }

    @PostConstruct
    public void init() {
        this.fields = new String[8];
        this.fields[0] = "idea.title";
        this.fields[1] = "idea.snackPeak";
        this.fields[2] = "idea.problem.title";
        this.fields[3] = "idea.problem.text";
        this.fields[4] = "idea.owner.firstName";
        this.fields[5] = "idea.owner.lastName";
        this.fields[6] = "idea.owner.email";
        this.fields[7] = "text";
    }

    @Override
    public Iterable<Solution> findAll() {
        return solutionRepository.findAll();
    }

    @Override
    public Solution save(Solution solution) {
        return solutionRepository.save(solution);
    }

    @Override
    public void delete(Long id) {
        solutionRepository.delete(id);
    }

    @Override
    public Solution findById(Long id) {
        return solutionRepository.findOne(id);
    }

    @Override
    public Solution add(Solution object) {
        return save(object);
    }

    @Override
    public Iterable<? extends BaseEntity> findAll(String query, Integer offset, Integer limit, SolutionFilter filter) {
        return queryService.search(query, filter.getQuery(), offset, limit, Solution.class, fields);
    }
}
