package com.bottle.team.repository;

import com.bottle.team.model.helpers.Currency;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by Viki on 2/21/2017.
 */
public interface CurrencyRepository extends GraphRepository<Currency> {
}
