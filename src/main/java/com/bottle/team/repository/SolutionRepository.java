package com.bottle.team.repository;

import com.bottle.team.model.ideas.Solution;
import com.bottle.team.model.sharing.Shareable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Viki on 10/15/2016.
 */
public interface SolutionRepository extends Neo4jRepository<Solution, Long> {
    @Query("MATCH (n:Solution) WHERE id(n) = {0} WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    Solution findOne(Long id);

    @Query("MATCH (n:Solution) WHERE id(n) in {0} WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    Iterable<Solution> findAllById(Iterable<Long> ids);

    @Query("MATCH (n:Solution)-[:IDEA]-(i:Idea) WHERE id(i) = {0} WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    Solution findByIdeaId(Long id);
}
