package com.bottle.team.repository;

import com.bottle.team.model.helpers.Currency;
import com.bottle.team.model.ideas.Solution;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Viki on 2/21/2017.
 */
public interface CurrencyRepository extends Neo4jRepository<Currency, Long> {
    @Query("MATCH (n:Currency) WHERE id(n) in {0} WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    Iterable<Currency> findAllById(Iterable<Long> ids);
}
