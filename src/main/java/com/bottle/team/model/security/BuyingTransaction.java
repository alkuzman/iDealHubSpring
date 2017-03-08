package com.bottle.team.model.security;

import com.bottle.team.model.ideas.Idea;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by Viki on 3/2/2017.
 */
@NodeEntity
public class BuyingTransaction extends ProtocolTransaction {
    @Relationship(type = "IDEA")
    private Idea idea;

    public Idea getIdea() {
        return idea;
    }

    public void setIdea(Idea idea) {
        this.idea = idea;
    }
}

