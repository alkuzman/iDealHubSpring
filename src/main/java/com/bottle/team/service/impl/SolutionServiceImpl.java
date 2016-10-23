package com.bottle.team.service.impl;

import com.bottle.team.model.ideas.Solution;
import com.bottle.team.repository.SolutionRepository;
import com.bottle.team.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Viki on 10/15/2016.
 */
@Service
public class SolutionServiceImpl implements SolutionService {
    @Autowired
    SolutionRepository solutionRepository;

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
}
