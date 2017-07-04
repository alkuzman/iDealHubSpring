package com.bottle.team.repository;

import com.bottle.team.model.sharing.Shareable;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by AKuzmanoski on 04/01/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 04/01/2017
 */
public interface SharableRepository extends GraphRepository<Shareable> {

}
