package com.bottle.team.model.payment;

import com.bottle.team.model.BaseEntityImpl;
import com.bottle.team.model.helpers.Currency;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by Viki on 2/21/2017.
 */
@NodeEntity
public class Money extends BaseEntityImpl {
    @Property(name = "value")
    private Double value;
    @Relationship
    private Currency currency;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
