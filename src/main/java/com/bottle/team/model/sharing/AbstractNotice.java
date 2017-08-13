package com.bottle.team.model.sharing;

import com.bottle.team.lucene.annotations.Boost;
import com.bottle.team.lucene.annotations.Indexed;
import com.bottle.team.lucene.annotations.IndexedEmbedded;
import com.bottle.team.model.BaseEntityImpl;
import com.bottle.team.model.authentication.Agent;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Date;

/**
 * Created by PC on 23/10/2016.
 */
@NodeEntity
public abstract class AbstractNotice extends BaseEntityImpl implements Notice {
    @Relationship(type = "RECIPIENT")
    @IndexedEmbedded
    @Boost(1f)
    private Agent recipient;

    @Property(name = "seen")
    private Date seen;

    @Property(name = "opened")
    private Date opened;

    @Override
    public Agent getRecipient() {
        return recipient;
    }

    @Override
    public void setRecipient(Agent recipient) {
        this.recipient = recipient;
    }

    public Date getSeen() {
        return seen;
    }

    public void setSeen(Date seen) {
        this.seen = seen;
    }

    public Date getOpened() {
        return opened;
    }

    public void setOpened(Date opened) {
        this.opened = opened;
    }
}
