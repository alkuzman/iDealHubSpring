package com.bottle.team.repository;

import com.bottle.team.model.security.ProtocolSession;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ProtocolSessionRepository extends Neo4jRepository<ProtocolSession, Long> {
}
