package com.bottle.team.model.security.notices;

import com.bottle.team.model.authentication.Agent;
import com.bottle.team.model.ideas.Idea;
import com.bottle.team.model.security.ProtocolSession;
import com.bottle.team.model.sharing.AbstractNotice;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by Viki on 3/9/2017.
 */
public abstract class AbstractProtocolTransactionStepNotice<T extends ProtocolTransactionStepNotice> extends AbstractNotice
        implements ProtocolTransactionStepNotice<T> {
    @Property(name = "message")
    private String message;

    @Relationship(type = "ORIGINATOR")
    private Agent originator;

    @Relationship(type = "PREVIOUS")
    private T previousStepNotice;

    @Relationship(type = "SESSION")
    private ProtocolSession protocolSession;

    @Property(name = "activated")
    private boolean activated;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Agent getOriginator() {
        return originator;
    }

    public void setOriginator(Agent originator) {
        this.originator = originator;
    }

    public T getPreviousStepNotice() {
        return previousStepNotice;
    }

    public void setPreviousStepNotice(T previousStepNotice) {
        this.previousStepNotice = previousStepNotice;
    }

    public ProtocolSession getProtocolSession() {
        return protocolSession;
    }

    public void setProtocolSession(ProtocolSession protocolSession) {
        this.protocolSession = protocolSession;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }
}
