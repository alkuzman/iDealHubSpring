package com.bottle.team.repository;

import com.bottle.team.model.security.ProtocolSession;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ProtocolSessionRepository extends Neo4jRepository<ProtocolSession, Long> {

    @Query("MATCH (n:ProtocolSession) WHERE n.epoId = {0} WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    ProtocolSession findByEpoId(String epoId);
}
