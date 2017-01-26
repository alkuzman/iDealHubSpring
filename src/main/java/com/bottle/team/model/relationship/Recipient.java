package com.bottle.team.model.relationship;

import com.bottle.team.model.authentication.Agent;
import com.bottle.team.model.sharing.Notice;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import java.util.Date;

/**
 * Created by Viki on 1/25/2017.
 */
@RelationshipEntity(type = "MEMBER")
public class Recipient extends BaseRelationship {
    @StartNode
    @JsonIgnore
    Notice notice;
    @EndNode
    Agent agent;
    private Date seen;
}
