package com.bottle.team.repository;

import com.bottle.team.model.ideas.Problem;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by Viki on 10/14/2016.
 */
public interface ProblemRepository extends GraphRepository<Problem> {
}
