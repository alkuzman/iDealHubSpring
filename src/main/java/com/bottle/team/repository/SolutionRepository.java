package com.bottle.team.repository;

import com.bottle.team.model.ideas.Idea;
import com.bottle.team.model.ideas.Solution;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by Viki on 10/15/2016.
 */
public interface SolutionRepository extends GraphRepository<Solution> {
    @Override
    @Query("MATCH (n) WHERE id(n) = { id } WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    Solution findOne(@Param("id") Long id);
}
