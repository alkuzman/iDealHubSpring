package com.bottle.team.repository;

import com.bottle.team.model.ideas.Solution;
import com.bottle.team.model.sharing.Announcement;
import com.bottle.team.model.sharing.Notice;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Viki on 1/26/2017.
 */
public interface NoticeRepository extends Neo4jRepository<Notice, Long> {
    @Query("MATCH (n:AbstractNotice)-[r:RECIPIENT]->(recipient:Agent) WHERE recipient.email = {0} WITH n SKIP {2} LIMIT {1} MATCH p=(n)-[*0..]->(m) RETURN n, p ORDER BY n.creationDate DESC")
    Iterable<Notice> getNotices(String recipientEmail, Integer limit, Integer offset);

    @Query("MATCH (n:AbstractNotice)-[r:RECIPIENT]->(recipient:Agent) WHERE recipient.email = {0} AND NOT exists(n.seen) WITH COUNT(n) as count RETURN count")
    Integer getCount(String email);

    @Query("MATCH (n:AbstractNotice)-[r:RECIPIENT]->(recipient:Agent) WHERE recipient.email = {0} AND NOT exists(n.seen) SET n.seen = {1} WITH COUNT(n) as count RETURN count ")
    void markAsSeen(String email, String now);

    @Query("MATCH (n:AbstractNotice)-[r:RECIPIENT]->(recipient:Agent) WHERE recipient.email = {0} AND NOT exists(n.seen) RETURN n")
    Iterable<Notice> getNotSeenNotices(String email);

    @Query("MATCH (n:Notice) WHERE id(n) = {0} WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    Notice findOne(Long id);

    @Query("MATCH (n:Notice) WHERE id(n) in {0} WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    Iterable<Notice> findAllById(Iterable<Long> ids);
}
