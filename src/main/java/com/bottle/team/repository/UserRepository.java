package com.bottle.team.repository;

import com.bottle.team.model.authentication.User;
import com.bottle.team.model.ideas.Solution;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by PC on 09/10/2016.
 */
public interface UserRepository extends Neo4jRepository<User, Long> {
    @Query("MATCH (n:User) WHERE n.email = {0} WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    User findByEmail(String email);

    @Query("MATCH (n) WHERE id(n) = {0} WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    User findOne(Long id);

    @Query("MATCH (n:User) WHERE n.activationCode = {0} WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    User findByActivationCode(String code);

    @Query("MATCH (n:User) WHERE id(n) in {0} WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    Iterable<User> findAllById(Iterable<Long> ids);
}
