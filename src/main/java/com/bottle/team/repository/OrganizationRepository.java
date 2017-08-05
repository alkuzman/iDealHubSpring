package com.bottle.team.repository;

import com.bottle.team.model.authentication.Organization;
import com.bottle.team.model.ideas.Solution;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by PC on 09/10/2016.
 */
public interface OrganizationRepository extends Neo4jRepository<Organization, Long> {
    @Query("MATCH (n) WHERE id(n) = {0} WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    Organization findOne(Long id);

    @Query("MATCH (n:Organization) WHERE id(n) in {0} WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    Iterable<Organization> findAllById(Iterable<Long> ids);
}
