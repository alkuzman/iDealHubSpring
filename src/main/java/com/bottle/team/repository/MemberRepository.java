package com.bottle.team.repository;

import com.bottle.team.model.authentication.Member;
import com.bottle.team.model.authentication.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by Viki on 10/15/2016.
 */
public interface MemberRepository extends GraphRepository<Member> {
    @Override
    @Query("MATCH (n) WHERE id(n) = { id } WITH n MATCH p=(n)-[*0..]->(m) RETURN p")
    Member findOne(@Param("id") Long id);
}
