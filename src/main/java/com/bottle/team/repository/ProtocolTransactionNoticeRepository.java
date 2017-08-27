package com.bottle.team.repository;

import com.bottle.team.model.security.notices.ProtocolTransactionStepFiveNotice;
import com.bottle.team.model.security.notices.ProtocolTransactionStepNotice;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ProtocolTransactionNoticeRepository extends Neo4jRepository<ProtocolTransactionStepNotice, Long> {

    @Query("MATCH (n:ProtocolTransactionStepFiveNotice)-[:SESSION]->(ps:ProtocolSession) WHERE ID(ps) = {0} WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    ProtocolTransactionStepFiveNotice findProtocolTransactionStepFiveNoticeByProtocolSessionId(Long protocolSessionId);
}
