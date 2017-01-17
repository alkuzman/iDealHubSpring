package com.bottle.team.repository;

import com.bottle.team.model.authentication.User;
import com.bottle.team.model.sharing.Announcement;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by AKuzmanoski on 04/01/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 04/01/2017
 */
public interface AnnouncementRepository extends GraphRepository<Announcement> {
    @Override
    @Query("MATCH (n) WHERE id(n) = { id } WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    Announcement findOne(@Param("id") Long id);
}
