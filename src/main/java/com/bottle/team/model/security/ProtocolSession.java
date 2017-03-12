package com.bottle.team.model.security;

import com.bottle.team.model.BaseEntityImpl;
import com.bottle.team.model.ideas.Idea;
import com.bottle.team.model.relationship.ProtocolParticipantSessionData;
import com.bottle.team.model.security.notices.ProtocolTransactionStepOneNotice;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/**
 * Created by Viki on 3/2/2017.
 */
@NodeEntity
public class ProtocolSession extends BaseEntityImpl {

    @Relationship(type = "PARTICIPANT")
    private List<ProtocolParticipantSessionData> participantsSessionData;

    @Relationship(type = "IDEA")
    private Idea idea;

    public List<ProtocolParticipantSessionData> getParticipantsSessionData() {
        return participantsSessionData;
    }

    public void setParticipantsSessionData(List<ProtocolParticipantSessionData> participantsSessionData) {
        this.participantsSessionData = participantsSessionData;
        for (ProtocolParticipantSessionData participant : participantsSessionData) {
            participant.setSession(this);
        }
    }

    public Idea getIdea() {
        return idea;
    }

    public void setIdea(Idea idea) {
        this.idea = idea;
    }
}
