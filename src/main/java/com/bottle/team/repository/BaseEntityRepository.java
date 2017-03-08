package com.bottle.team.repository;

import com.bottle.team.model.authentication.User;
import com.bottle.team.model.interfaces.BaseEntity;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by AKuzmanoski on 14/01/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 14/01/2017
 */
public interface BaseEntityRepository extends GraphRepository<BaseEntity> {
    @Query("MATCH (n:BaseEntity) WHERE id(n) in { ids } WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    Iterable<BaseEntity> findAll(@Param("ids") Iterable<Long> ids);

    @Override
    @Query("MATCH (n) WHERE id(n) = { id } WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    BaseEntity findOne(@Param("id") Long id);
}
