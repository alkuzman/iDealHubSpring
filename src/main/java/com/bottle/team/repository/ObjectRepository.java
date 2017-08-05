package com.bottle.team.repository;

import com.bottle.team.model.interfaces.BaseEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AKuzmanoski on 14/01/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 14/01/2017
 */
public interface ObjectRepository<T extends BaseEntity> extends Neo4jRepository<T, Long> {
}
