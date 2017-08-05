package com.bottle.team.repository;

import com.bottle.team.model.ideas.Solution;
import com.bottle.team.model.interfaces.BaseEntity;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by AKuzmanoski on 14/01/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 14/01/2017
 */
public interface BaseEntityRepository extends Neo4jRepository<BaseEntity, Long> {
    @Query("MATCH (n:BaseEntity) WHERE id(n) in {0} WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    Iterable<BaseEntity> findAll(Iterable<Long> ids);

    @Query("MATCH (n:BaseEntity) WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    Iterable<BaseEntity> findAll();

    @Query("MATCH (n) WHERE id(n) = {0} WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    BaseEntity findOne(Long id);

    @Query("MATCH (n:BaseEntity) WHERE id(n) in {0} WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    Iterable<BaseEntity> findAllById(Iterable<Long> ids);
}
