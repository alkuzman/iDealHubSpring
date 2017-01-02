package com.bottle.team.model.ideas;

import com.bottle.team.model.BaseEntityImpl;
import com.bottle.team.model.authentication.Person;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by PC on 23/10/2016.
 */
@NodeEntity
public class Deal extends BaseEntityImpl {
    @Relationship(type = "CONTRACT")
    private Contract contract;

    @Relationship(type = "IDEA")
    private Idea idea;

    @Relationship(type = "BUYER")
    private Person buyer;

    public Idea getIdea() {
        return idea;
    }

    public void setIdea(Idea idea) {
        this.idea = idea;
    }

    public Person getBuyer() {
        return buyer;
    }

    public void setBuyer(Person buyer) {
        this.buyer = buyer;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
