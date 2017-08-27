package com.bottle.team.model.relationship;

import com.bottle.team.model.authentication.Agent;
import com.bottle.team.model.security.ProtocolSession;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

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

    @Property(name = "sessionKey")
    private String sessionKeyEncrypted;

    @Property(name = "encryptedGoods")
    private String encryptedGoods;

    @Property(name = "nonce")
    private String nonce;

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

    public String getEncryptedGoods() {
        return encryptedGoods;
    }

    public void setEncryptedGoods(String encryptedGoods) {
        this.encryptedGoods = encryptedGoods;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }
}
