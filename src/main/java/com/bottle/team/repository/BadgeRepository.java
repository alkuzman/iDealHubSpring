package com.bottle.team.repository;

import com.bottle.team.model.awards.bedges.Badge;
import com.bottle.team.model.interfaces.BaseEntity;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by AKuzmanoski on 06/03/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 06/03/2017
 */
public interface BadgeRepository extends GraphRepository<Badge> {
    @Query("MATCH (n:Badge) WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    Iterable<Badge> findAll();

    @Query("MATCH (n:Badge) WHERE { type } in labels(n) WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    Iterable<Badge> findByType(@Param("type") String type);
}
