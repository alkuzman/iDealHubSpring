package com.bottle.team.model.security.notices;

import com.bottle.team.model.authentication.Agent;
import com.bottle.team.model.ideas.Idea;
import com.bottle.team.model.interfaces.BaseEntity;
import com.bottle.team.model.security.ProtocolSession;
import com.bottle.team.model.sharing.Notice;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by Viki on 3/9/2017.
 */
@NodeEntity
@JsonSubTypes({
        @JsonSubTypes.Type(value = AbstractProtocolTransactionStepNotice.class),
        @JsonSubTypes.Type(value = ProtocolTransactionStepOneNotice.class),
        @JsonSubTypes.Type(value = ProtocolTransactionStepTwoNotice.class),
        @JsonSubTypes.Type(value = ProtocolTransactionStepThreeNotice.class),
        @JsonSubTypes.Type(value = ProtocolTransactionStepFourNotice.class)
})
public interface ProtocolTransactionStepNotice<T extends ProtocolTransactionStepNotice> extends Notice {

    String getMessage();

    void setMessage(String message);

    Agent getOriginator();

    void setOriginator(Agent originator);

    T getPreviousStepNotice();

    void setPreviousStepNotice(T previousStepNotice);

    ProtocolSession getProtocolSession();

    void setProtocolSession(ProtocolSession protocolSession);

    boolean isActivated();

    void setActivated(boolean activated);
}
