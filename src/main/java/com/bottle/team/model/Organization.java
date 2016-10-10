package com.bottle.team.model;

import com.bottle.team.model.relationship.Member;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/**
 * Created by PC on 09/10/2016.
 */
public class Organization extends Agent {
    @Relationship(type = "MEMBER")
    List<Member> members;
}
