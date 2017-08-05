package com.bottle.team.repository;

import com.bottle.team.model.interfaces.BaseEntity;
import com.bottle.team.model.sharing.Searchable;
import com.bottle.team.model.sharing.Shareable;
import org.springframework.data.neo4j.annotation.Depth;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by AKuzmanoski on 04/01/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 04/01/2017
 */
public interface ShareableRepository extends Neo4jRepository<Shareable, Long> {
    @Query("MATCH (n:Shareable) WHERE id(n) = {0} WITH n MATCH p=(n)-[*0..]->(m) RETURN n, p")
    Shareable findOne(Long id);

    @Depth(2)
    Optional<Shareable> findById(Long id);

    @Query("MATCH (n:Shareable) WHERE id(n) in {0} WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    Iterable<Shareable> findAllById(Iterable<Long> ids);
}
