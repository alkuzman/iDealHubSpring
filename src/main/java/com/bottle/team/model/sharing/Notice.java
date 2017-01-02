package com.bottle.team.model.sharing;

import com.bottle.team.model.BaseEntityImpl;
import com.bottle.team.model.authentication.Agent;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by PC on 23/10/2016.
 */
@NodeEntity
public abstract class Notice extends BaseEntityImpl {
    @Relationship(type = "RECIPIENT")
    private Agent recipient;

    public Agent getRecipient() {
        return recipient;
    }

    public void setRecipient(Agent recipient) {
        this.recipient = recipient;
    }
}
