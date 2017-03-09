package com.bottle.team.model.security;

import com.bottle.team.model.BaseEntityImpl;
import com.bottle.team.model.authentication.Agent;
import com.bottle.team.model.enumaration.ProtocolTransactionMessageNumber;
import com.oracle.webservices.internal.api.message.PropertySet;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by Viki on 3/2/2017.
 */
@NodeEntity
public class ProtocolTransactionStep extends BaseEntityImpl {
    @Property(name = "stepNumber")
    private ProtocolTransactionMessageNumber stepNumber;

    @Property(name = "stepMessage")
    private String stepMessage;

    @Relationship(type = "CREATOR")
    private Agent creator;

    public ProtocolTransactionMessageNumber getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(ProtocolTransactionMessageNumber stepNumber) {
        this.stepNumber = stepNumber;
    }

    public String getStepMessage() {
        return stepMessage;
    }

    public void setStepMessage(String stepMessage) {
        this.stepMessage = stepMessage;
    }

    public Agent getCreator() {
        return creator;
    }

    public void setCreator(Agent creator) {
        this.creator = creator;
    }
}
