package com.bottle.team.model.relationship;

import com.bottle.team.model.authentication.Agent;
import com.bottle.team.model.security.ProtocolSession;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.*;

/**
 * Created by Viki on 3/21/2017.
 */
@RelationshipEntity(type = "PARTICIPANT_TWO")
public class ProtocolParticipantTwoSessionData extends BaseRelationship {

    @StartNode
    @JsonIgnore
    private ProtocolSession session;

    @EndNode
    private Agent participant;

    @Property(name = "session_key")
    private String sessionKeyEncrypted;

    public ProtocolSession getSession() {
        return session;
    }

    public void setSession(ProtocolSession session) {
        this.session = session;
    }

    public Agent getParticipant() {
        return participant;
    }

    public void setParticipant(Agent participant) {
        this.participant = participant;
    }

    public String getSessionKeyEncrypted() {
        return sessionKeyEncrypted;
    }

    public void setSessionKeyEncrypted(String sessionKeyEncrypted) {
        this.sessionKeyEncrypted = sessionKeyEncrypted;
    }
}
