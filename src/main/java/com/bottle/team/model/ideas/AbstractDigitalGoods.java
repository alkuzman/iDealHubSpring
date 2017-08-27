package com.bottle.team.model.ideas;

import com.bottle.team.lucene.annotations.Boost;
import com.bottle.team.lucene.annotations.IndexedEmbedded;
import com.bottle.team.model.BaseEntityImpl;
import com.bottle.team.model.authentication.User;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class AbstractDigitalGoods extends BaseEntityImpl implements DigitalGoods {
    @Relationship(type = "OWNER")
    @IndexedEmbedded
    @Boost(0.5f)
    private User owner;

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
