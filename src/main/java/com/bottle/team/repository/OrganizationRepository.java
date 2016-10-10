package com.bottle.team.repository;

import com.bottle.team.model.Organization;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by PC on 09/10/2016.
 */
public interface OrganizationRepository extends GraphRepository<Organization> {

}
