package com.bottle.team.repository;

import com.bottle.team.model.ideas.Solution;
import com.bottle.team.model.sharing.Announcement;
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
public interface AnnouncementRepository extends Neo4jRepository<Announcement, Long> {
    @Query("MATCH (n:Announcement) WHERE id(n) = {0} WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    Announcement findOne(Long id);

    @Query("MATCH (n:Announcement) WHERE id(n) in {0} WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    Iterable<Announcement> findAllById(Iterable<Long> ids);
}
