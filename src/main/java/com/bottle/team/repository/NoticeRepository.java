package com.bottle.team.repository;

import com.bottle.team.model.sharing.Notice;
import com.bottle.team.model.sharing.Searchable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by Viki on 1/26/2017.
 */
public interface NoticeRepository extends GraphRepository<Notice> {
    @Query("MATCH (n:Notice)-[r:RECIPIENT]->(recipient:Agent) WHERE recipient.email = { recipient_email } WITH n MATCH p=(n)-[*0..]->(m) RETURN n, p ORDER BY n.creationDate DESC SKIP { offset } LIMIT { limit }")
    Iterable<Notice> getNotices(@Param("recipient_email") String recipientEmail, @Param("limit") Integer limit, @Param("offset") Integer offset);

    @Query("MATCH (n:Notice)-[r:RECIPIENT]->(recipient:Agent) WHERE recipient.email = { recipient_email } AND NOT exists(r.seen) WITH COUNT(n) as count RETURN count")
    Integer getCount(@Param("recipient_email") String email);
}
