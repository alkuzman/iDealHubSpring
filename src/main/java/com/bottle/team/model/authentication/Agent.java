package com.bottle.team.model.authentication;

import com.bottle.team.model.BaseEntityImpl;
import org.neo4j.ogm.annotation.Property;

/**
 * Created by PC on 09/10/2016.
 */
public class Agent extends BaseEntityImpl {
    @Property(name = "email")
    private String email;
    @Property(name = "telephone")
    private String telephone;

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
