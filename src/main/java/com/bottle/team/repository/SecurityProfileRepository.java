package com.bottle.team.repository;

import com.bottle.team.model.ideas.Solution;
import com.bottle.team.model.security.SecurityProfile;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Viki on 2/12/2017.
 */
public interface SecurityProfileRepository extends Neo4jRepository<SecurityProfile, Long> {

    /*
    @Query("MATCH (s:SecurityProfile)-[:IDENTITY]->(u:Agent) WHERE u.email = {email} AND s.certificateType = {type} RETURN s")
    SecurityProfile findByUserEmailAndCertificateType(@Param("email") String email, @Param("type") CertificateType type);
    */

    @Query("MATCH (s:SecurityProfile)-[:IDENTITY]->(u:Agent) WHERE u.email = {0} WITH s MATCH p=(s)-[*0..]->(m) RETURN p")
    SecurityProfile findByUserEmail(String email);

    @Query("MATCH (n:SecurityProfile) WHERE id(n) in {0} WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    Iterable<SecurityProfile> findAllById(Iterable<Long> ids);
}
