package com.bottle.team.model.relationship;

import com.bottle.team.model.authentication.Agent;
import com.bottle.team.model.security.ProtocolSession;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.*;

/**
 * Created by Viki on 3/9/2017.
 */
@RelationshipEntity(type = "PARTICIPANT_ONE")
public class ProtocolParticipantOneSessionData extends BaseRelationship {

    @StartNode
    @JsonIgnore
    private ProtocolSession session;

    @EndNode
    private Agent participant;

    @Property(name = "session_key")
    private String sessionKeyEncrypted;

    @Property(name = "data_encryption_key")
    private String dataEncryptionKey;

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

    public String getDataEncryptionKey() {
        return dataEncryptionKey;
    }

    public void setDataEncryptionKey(String dataEncryptionKey) {
        this.dataEncryptionKey = dataEncryptionKey;
    }
}
