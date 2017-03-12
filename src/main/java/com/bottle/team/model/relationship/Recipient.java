package com.bottle.team.model.relationship;

import com.bottle.team.model.authentication.Agent;
import com.bottle.team.model.sharing.AbstractNotice;
import com.bottle.team.model.sharing.Notice;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import java.util.Date;

/**
 * Created by Viki on 1/25/2017.
 */
@RelationshipEntity(type = "RECIPIENT")
public class Recipient extends BaseRelationship {
    @StartNode
    @JsonIgnore
    Notice notice;
    @EndNode
    Agent agent;
    @Property(name = "seen")
    private Date seen;

    public Notice getNotice() {
        return notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Date getSeen() {
        return seen;
    }

    public void setSeen(Date seen) {
        this.seen = seen;
    }
}
