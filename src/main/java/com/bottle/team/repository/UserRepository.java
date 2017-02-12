package com.bottle.team.repository;

import com.bottle.team.model.authentication.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by PC on 09/10/2016.
 */
public interface UserRepository extends GraphRepository<User> {
    @Query("MATCH (n:User) WHERE n.email = { email } WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    User findByEmail(@Param("email") String email);

    @Override
    @Query("MATCH (n) WHERE id(n) = { id } WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    User findOne(@Param("id") Long id);

    @Query("MATCH (n:User) WHERE n.activationCode = { code } WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    User findByActivationCode(@Param("code") String code);
}
