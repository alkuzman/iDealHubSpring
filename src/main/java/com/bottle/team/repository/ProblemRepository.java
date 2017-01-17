package com.bottle.team.repository;

import com.bottle.team.model.ideas.Idea;
import com.bottle.team.model.ideas.Problem;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by Viki on 10/14/2016.
 */
public interface ProblemRepository extends GraphRepository<Problem> {
    @Query(value = "MATCH (n:`Problem`) MATCH (m0:`User`) WHERE ID(m0) = { `questioner_id` } MATCH (n)-[:`QUESTIONER`]-(m0) WITH n MATCH p=(n)-[*0..1]-(m) RETURN p, ID(n)")
    Iterable<Problem> findByQuestioner_Id(@Param("questioner_id") Long questionerId);

    @Override
    @Query("MATCH (n) WHERE id(n) = { id } WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    Problem findOne(@Param("id") Long id);
}
