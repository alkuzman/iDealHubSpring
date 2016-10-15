package com.bottle.team.model;

import org.neo4j.ogm.annotation.Property;

/**
 * Created by PC on 09/10/2016.
 */
public class Agent extends BaseEntity {
    @Property(name = "email")
    String email;
    @Property(name = "telephone")
    String telephone;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
