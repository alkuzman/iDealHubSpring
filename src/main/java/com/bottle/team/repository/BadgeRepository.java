package com.bottle.team.repository;

import com.bottle.team.model.awards.badges.Badge;
import com.bottle.team.model.ideas.Solution;
import com.bottle.team.model.sharing.Announcement;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AKuzmanoski on 06/03/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 06/03/2017
 */
public interface BadgeRepository extends Neo4jRepository<Badge, Long> {
    @Query("MATCH (n:Badge) WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    Iterable<Badge> findAll();

    @Query("MATCH (n:Badge) WHERE {0} in labels(n) WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    Iterable<Badge> findByType(String type);

    @Query("MATCH (n:Badge) WHERE id(n) = {0} WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    Badge findOne(Long id);

    @Query("MATCH (n:Badge) WHERE id(n) in {0} WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    Iterable<Badge> findAllById(Iterable<Long> ids);
}
