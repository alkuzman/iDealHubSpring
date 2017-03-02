package com.bottle.team.model.sharing;

import com.bottle.team.model.BaseEntityImpl;
import com.bottle.team.model.ideas.Idea;
import com.bottle.team.model.ideas.Problem;
import com.bottle.team.model.relationship.Recipient;
import com.bottle.team.model.security.NewProtocolTransactionNotice;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/**
 * Created by PC on 23/10/2016.
 */
@NodeEntity
@JsonSubTypes({
        @JsonSubTypes.Type(value = NewCommentNotice.class),
        @JsonSubTypes.Type(value = NewPackageNotice.class),
        @JsonSubTypes.Type(value = NewProtocolTransactionNotice.class)
})
public abstract class Notice extends BaseEntityImpl {
    @Relationship(type = "RECIPIENT")
    private List<Recipient> recipients;

    public List<Recipient> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<Recipient> recipients) {
        this.recipients = recipients;
    }
}
