package com.bottle.team.model.ideas;

import com.bottle.team.model.BaseEntityImpl;
import com.bottle.team.model.authentication.Person;
import com.bottle.team.model.payment.Contract;
import com.bottle.team.model.payment.Payment;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by PC on 23/10/2016.
 */
@NodeEntity
public class Deal extends BaseEntityImpl {
    @Relationship(type = "PAYMENT")
    private Payment payments;

    @Relationship(type = "GOODS")
    private DigitalGoods goods;

    @Relationship(type = "BUYER")
    private Person buyer;

    @Property(name = "epoId")
    private String epoId;

    public Payment getPayments() {
        return payments;
    }

    public void setPayments(Payment payments) {
        this.payments = payments;
    }

    public DigitalGoods getGoods() {
        return goods;
    }

    public void setGoods(DigitalGoods goods) {
        this.goods = goods;
    }

    public Person getBuyer() {
        return buyer;
    }

    public void setBuyer(Person buyer) {
        this.buyer = buyer;
    }

    public String getEpoId() {
        return epoId;
    }

    public void setEpoId(String epoId) {
        this.epoId = epoId;
    }
}
