package com.bottle.team.repository;

import com.bottle.team.model.ideas.Solution;
import com.bottle.team.model.sharing.Searchable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by AKuzmanoski on 20/01/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 20/01/2017
 */
public interface SearchableRepository extends Neo4jRepository<Searchable, Long> {
    @Query("MATCH (n) WHERE id(n) = {0} WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    Searchable findOne(Long id);

    @Query("MATCH (n:Searchable) WHERE id(n) in {0} WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    Iterable<Searchable> findAllById(Iterable<Long> ids);
}
