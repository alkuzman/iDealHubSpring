package com.bottle.team.repository;

import com.bottle.team.model.authentication.Member;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by Viki on 10/15/2016.
 */
public interface MemberRepository extends GraphRepository<Member> {
}
