package com.bottle.team.model;

import com.bottle.team.model.interfaces.NamedEntity;
import com.bottle.team.model.interfaces.Questioner;
import org.neo4j.ogm.annotation.Property;

/**
 * Created by PC on 09/10/2016.
 */
public class Person extends Agent implements Questioner, NamedEntity {
    @Property(name = "firstName")
    String firstName;
    @Property(name = "lastName")
    String lastName;
    @Property(name = "password")
    String password;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String constructName() {
        return String.format("%s %s", firstName, lastName);
    }
}
