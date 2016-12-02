package com.bottle.team.repository;

import com.bottle.team.model.ideas.Idea;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by PC on 09/10/2016.
 */
public interface IdeaRepository extends GraphRepository<Idea> {
    // derived finder
    Idea findByTitle(String title);

    /*
    @Query("MATCH (m:Movie)<-[rating:RATED]-(user) WHERE id(movie)={movie} return rating")
    List<Rating> getRatings(@Param("movie") Movie movie);*/

    @Query(value = "MATCH (n:`Idea`) MATCH (m0:`Problem`) WHERE ID(m0) = { `problem_id` } MATCH (n)-[:`PROBLEM`]-(m0) WITH n MATCH p=(n)-[*0..1]-(m) RETURN p, ID(n)")
    Iterable<Idea> findByProblem_Id(@Param("problem_id") Long id);
}
