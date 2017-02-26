package com.bottle.team.repository;

import com.bottle.team.model.security.SecurityProfile;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by Viki on 2/12/2017.
 */
public interface SecurityProfileRepository extends GraphRepository<SecurityProfile> {

    /*
    @Query("MATCH (s:SecurityProfile)-[:IDENTITY]->(u:Agent) WHERE u.email = {email} AND s.certificateType = {type} RETURN s")
    SecurityProfile findByUserEmailAndCertificateType(@Param("email") String email, @Param("type") CertificateType type);
    */

    @Query("MATCH (s:SecurityProfile)-[:IDENTITY]->(u:Agent) WHERE u.email = {email} WITH s MATCH p=(s)-[*0..]->(m) RETURN p")
    SecurityProfile findByUserEmail(@Param("email") String email);
}
