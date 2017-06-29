package com.bottle.team.model.sharing;

import com.bottle.team.model.BaseEntityImpl;
import com.bottle.team.model.relationship.Recipient;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/**
 * Created by PC on 23/10/2016.
 */
@NodeEntity
public abstract class AbstractNotice extends BaseEntityImpl implements Notice {
    @Relationship(type = "RECIPIENT")
    private List<Recipient> recipients;

    @Override
    public List<Recipient> getRecipients() {
        return recipients;
    }

    @Override
    public void setRecipients(List<Recipient> recipients) {
        this.recipients = recipients;
        if (this.recipients != null) {
            for (Recipient recipient : this.recipients)
                recipient.setNotice(this);
        }
    }
}
