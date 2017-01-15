package com.bottle.team.service.impl;

import com.bottle.team.model.ideas.Problem;
import com.bottle.team.model.interfaces.BaseEntity;
import com.bottle.team.repository.ProblemRepository;
import com.bottle.team.service.ProblemService;
import com.bottle.team.service.QueryService;
import com.bottle.team.service.helper.IdeaFilter;
import com.bottle.team.service.helper.ProblemFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by Viki on 10/14/2016.
 */
@Service
public class ProblemServiceImpl implements ProblemService {
    private final
    ProblemRepository problemRepository;
    private final
    QueryService queryService;
    private String[] fields;

    @Autowired
    public ProblemServiceImpl(QueryService queryService, ProblemRepository problemRepository) {
        this.queryService = queryService;
        this.problemRepository = problemRepository;
    }

    @PostConstruct
    public void init() {
        this.fields = new String[5];
        this.fields[0] = "title";
        this.fields[1] = "text";
        this.fields[2] = "questioner.firstName";
        this.fields[3] = "questioner.lastName";
        this.fields[4] = "questioner.email";
    }

    @Override
    public Iterable<Problem> findAll() {
        return problemRepository.findAll();
    }

    @Override
    public Problem save(Problem problem) {

        return problemRepository.save(problem);
    }

    @Override
    public Problem add(Problem object) {
        return save(object);
    }

    @Override
    public void delete(Long id) {
        problemRepository.delete(id);
    }

    @Override
    public Problem findById(Long id) {
        return problemRepository.findOne(id);
    }

    @Override
    public Iterable<Problem> findByQuestionerId(Long questionerId) {
        return problemRepository.findByQuestioner_Id(questionerId);
    }

    @Override
    public Iterable<? extends BaseEntity> findAll(String query, Integer offset, Integer limit, ProblemFilter filter) {
        return queryService.search(query, filter.getQuery(), offset, limit, Problem.class, fields);
    }
}
