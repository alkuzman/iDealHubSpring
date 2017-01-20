package com.bottle.team.repository;

import com.bottle.team.model.ideas.Problem;
import com.bottle.team.model.sharing.Searchable;
import com.bottle.team.model.sharing.Sharable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by AKuzmanoski on 20/01/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 20/01/2017
 */
public interface SearchableRepository extends GraphRepository<Searchable> {
    @Override
    @Query("MATCH (n) WHERE id(n) = { id } WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    Searchable findOne(@Param("id") Long id);
}
