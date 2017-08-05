package com.bottle.team.repository;

import com.bottle.team.model.ideas.Problem;
import com.bottle.team.model.ideas.Solution;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Viki on 10/14/2016.
 */
public interface ProblemRepository extends Neo4jRepository<Problem, Long> {
    @Query(value = "MATCH (n:`Problem`) MATCH (m0:`User`) WHERE ID(m0) = {0} MATCH (n)-[:`QUESTIONER`]-(m0) WITH n MATCH p=(n)-[*0..1]-(m) RETURN p, ID(n)")
    Iterable<Problem> findByQuestioner_Id(Long questionerId);

    @Query("MATCH (n) WHERE id(n) = {0} WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    Problem findOne(Long id);

    @Query("MATCH (n:Problem) WHERE id(n) in {0} WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    Iterable<Problem> findAllById(Iterable<Long> ids);
}
