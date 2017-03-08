package com.bottle.team.model.security;

import com.bottle.team.model.sharing.Notice;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by Viki on 3/2/2017.
 */
@NodeEntity
public class BuyingTransactionNotice extends Notice {
    @Relationship(type = "BUYING_TRANSACTION")
    private BuyingTransaction buyingTransaction;

    public BuyingTransaction getBuyingTransaction() {
        return buyingTransaction;
    }

    public void setBuyingTransaction(BuyingTransaction buyingTransaction) {
        this.buyingTransaction = buyingTransaction;
    }
}
