package com.bottle.team.service.impl;

import com.bottle.team.model.ideas.Problem;
import com.bottle.team.repository.ProblemRepository;
import com.bottle.team.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Viki on 10/14/2016.
 */
@Service
public class ProblemServiceImpl implements ProblemService {
    @Autowired
    ProblemRepository problemRepository;

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
}
