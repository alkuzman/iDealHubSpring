package com.bottle.team.repository;

import com.bottle.team.model.security.BuyingTransaction;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by Viki on 3/4/2017.
 */
public interface BuyingTransactionRepository extends GraphRepository<BuyingTransaction> {
}
