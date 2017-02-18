package com.bottle.team.repository;

import com.bottle.team.model.enumaration.CertificateType;
import com.bottle.team.model.security.SecurityProfile;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by Viki on 2/12/2017.
 */
public interface SecurityProfileRepository extends GraphRepository<SecurityProfile> {

    @Query("MATCH (s:SecurityProfile)-[:IDENTITY]->(u:Agent) WHERE ID(u)= {userId} AND s.certificateType = {type} RETURN s")
    SecurityProfile findByUserIdAndCertificateType(@Param("userId") Long userId, @Param("type") CertificateType type);
}
