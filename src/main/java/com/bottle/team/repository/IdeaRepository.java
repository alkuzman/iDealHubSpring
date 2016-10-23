package com.bottle.team.repository;

import com.bottle.team.model.ideas.Idea;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by PC on 09/10/2016.
 */
public interface IdeaRepository extends GraphRepository<Idea> {
    // derived finder
    Idea findByTitle(String title);

    /*
    @Query("MATCH (m:Movie)<-[rating:RATED]-(user) WHERE id(movie)={movie} return rating")
    List<Rating> getRatings(@Param("movie") Movie movie);*/

}
