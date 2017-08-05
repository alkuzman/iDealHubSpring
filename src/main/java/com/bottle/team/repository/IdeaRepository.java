package com.bottle.team.repository;

import com.bottle.team.model.ideas.Idea;
import com.bottle.team.model.ideas.Solution;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by PC on 09/10/2016.
 */
public interface IdeaRepository extends Neo4jRepository<Idea, Long> {
    // derived finder
    @Query("MATCH (n:Idea) WHERE n.title = {0} WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    Idea findByTitle(String title);

    @Query("MATCH (n) WHERE id(n) = {0} WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    Idea findOne(Long id);

    /*
    @Query("MATCH (m:Movie)<-[rating:RATED]-(user) WHERE id(movie)={movie} return rating")
    List<Rating> getRatings(@Param("movie") Movie movie);*/

    @Query(value = "MATCH (n:`Idea`) MATCH (m0:`Problem`) WHERE ID(m0) = {0} MATCH (n)-[:`PROBLEM`]-(m0) WITH n MATCH p=(n)-[*0..1]-(m) RETURN p, ID(n)")
    Iterable<Idea> findByProblem_Id(Long id);

    @Query(value = "MATCH (n:`Idea`) MATCH (m0:`User`) WHERE ID(m0) = {0} MATCH (n)-[:`OWNER`]-(m0) WITH n MATCH p=(n)-[*0..1]-(m) RETURN p, ID(n)")
    Iterable<Idea> findByOwner_Id(Long ownerId);

    @Query("MATCH (n:Idea) WHERE id(n) in {0} WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    Iterable<Idea> findAllById(Iterable<Long> ids);
}
