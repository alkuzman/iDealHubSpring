package com.bottle.team.repository;

import com.bottle.team.model.ideas.Solution;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by Viki on 10/15/2016.
 */
public interface SolutionRepository extends GraphRepository<Solution> {
}
