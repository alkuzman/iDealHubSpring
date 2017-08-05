package com.bottle.team.repository;

import com.bottle.team.model.authentication.Member;
import com.bottle.team.model.ideas.Solution;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Viki on 10/15/2016.
 */
public interface MemberRepository extends Neo4jRepository<Member, Long> {
    @Query("MATCH (n) WHERE id(n) = {0} WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    Member findOne(Long id);

    @Query("MATCH (n:Member) WHERE id(n) in {0} WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    Iterable<Member> findAllById(Iterable<Long> ids);
}
