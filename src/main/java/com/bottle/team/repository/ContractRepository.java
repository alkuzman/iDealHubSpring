package com.bottle.team.repository;

import com.bottle.team.model.payment.Contract;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ContractRepository extends Neo4jRepository<Contract, Long> {
}
