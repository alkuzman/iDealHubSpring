package com.bottle.team.repository;

import com.bottle.team.model.security.SecurityProfile;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by Viki on 2/12/2017.
 */
public interface SecurityProfileRepository extends GraphRepository<SecurityProfile> {
}
