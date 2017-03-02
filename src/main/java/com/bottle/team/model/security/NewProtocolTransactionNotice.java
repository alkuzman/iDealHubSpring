package com.bottle.team.model.security;

import com.bottle.team.model.sharing.Notice;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by Viki on 3/2/2017.
 */
@NodeEntity
public class NewProtocolTransactionNotice extends Notice {
    @Relationship(type = "PROTOCOL_TRANSACTION")
    private ProtocolTransaction protocolTransaction;

    public ProtocolTransaction getProtocolTransaction() {
        return protocolTransaction;
    }

    public void setProtocolTransaction(ProtocolTransaction protocolTransaction) {
        this.protocolTransaction = protocolTransaction;
    }
}
