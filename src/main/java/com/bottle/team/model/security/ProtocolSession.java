package com.bottle.team.model.security;

import com.bottle.team.model.BaseEntityImpl;
import com.bottle.team.model.ideas.DigitalGoods;
import com.bottle.team.model.relationship.ProtocolParticipantOneSessionData;
import com.bottle.team.model.relationship.ProtocolParticipantTwoSessionData;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by Viki on 3/2/2017.
 */
@NodeEntity
public class ProtocolSession extends BaseEntityImpl {

    // Todo: Rename Protocol Participant One and Two session data into customer and merchant or other more suitable names

    @Relationship(type = "PARTICIPANT_ONE")
    private ProtocolParticipantOneSessionData participantOneSessionData;

    @Relationship(type = "PARTICIPANT_TWO")
    private ProtocolParticipantTwoSessionData participantTwoSessionData;

    @Relationship(type = "DIGITAL_GOODS")
    private DigitalGoods digitalGoods;

    @Property(name = "protocolAborted")
    private boolean aborted;

    public DigitalGoods getDigitalGoods() {
        return digitalGoods;
    }

    public void setDigitalGoods(DigitalGoods digitalGoods) {
        this.digitalGoods = digitalGoods;
    }

    public ProtocolParticipantOneSessionData getParticipantOneSessionData() {
        return participantOneSessionData;
    }

    public void setParticipantOneSessionData(ProtocolParticipantOneSessionData participantOneSessionData) {
        this.participantOneSessionData = participantOneSessionData;
        this.participantOneSessionData.setSession(this);
    }

    public ProtocolParticipantTwoSessionData getParticipantTwoSessionData() {
        return participantTwoSessionData;
    }

    public void setParticipantTwoSessionData(ProtocolParticipantTwoSessionData participantTwoSessionData) {
        this.participantTwoSessionData = participantTwoSessionData;
        this.participantTwoSessionData.setSession(this);
    }

    public boolean isAborted() {
        return aborted;
    }

    public void setAborted(boolean aborted) {
        this.aborted = aborted;
    }
}
