package com.bottle.team.model.sharing;

import com.bottle.team.model.authentication.Agent;
import com.bottle.team.model.interfaces.BaseEntity;
import com.bottle.team.model.security.notices.ProtocolTransactionStepNotice;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.Date;

/**
 * Created by Viki on 3/9/2017.
 */
@NodeEntity
@JsonSubTypes({
        @JsonSubTypes.Type(value = AbstractNotice.class),
        @JsonSubTypes.Type(value = NewCommentNotice.class),
        @JsonSubTypes.Type(value = NewPackageNotice.class),
        @JsonSubTypes.Type(value = ProtocolTransactionStepNotice.class)
})
public interface Notice extends BaseEntity {

    Agent getRecipient();

    void setRecipient(Agent recipient);

    Date getSeen();

    void setSeen(Date date);

    Date getOpened();

    void setOpened(Date date);
}
