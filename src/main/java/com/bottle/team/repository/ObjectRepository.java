package com.bottle.team.repository;

import com.bottle.team.model.authentication.Organization;
import com.bottle.team.model.interfaces.BaseEntity;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by AKuzmanoski on 14/01/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 14/01/2017
 */
public interface ObjectRepository<T extends BaseEntity> extends GraphRepository<T> {
}
