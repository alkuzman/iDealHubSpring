package com.bottle.team.model.security;

import com.bottle.team.model.BaseEntityImpl;
import com.bottle.team.model.enumaration.ProtocolTransactionMessageNumber;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/**
 * Created by Viki on 3/2/2017.
 */
@NodeEntity
public class ProtocolTransaction extends BaseEntityImpl {
    @Relationship(type = "MESSAGE")
    private List<ProtocolTransactionStep> messages;

    @Property(name = "currentStep")
    private ProtocolTransactionMessageNumber currentStep;

    public List<ProtocolTransactionStep> getMessages() {
        return messages;
    }

    public void setMessages(List<ProtocolTransactionStep> messages) {
        this.messages = messages;
    }

    public ProtocolTransactionMessageNumber getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(ProtocolTransactionMessageNumber currentStep) {
        this.currentStep = currentStep;
    }
}
