package com.bottle.team.repository;

import com.bottle.team.model.authentication.Organization;
import com.bottle.team.model.authentication.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by PC on 09/10/2016.
 */
public interface OrganizationRepository extends GraphRepository<Organization> {
    @Override
    @Query("MATCH (n) WHERE id(n) = { id } WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    Organization findOne(@Param("id") Long id);
}
