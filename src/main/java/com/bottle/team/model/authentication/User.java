package com.bottle.team.model.authentication;

import com.bottle.team.model.enumaration.Provider;
import com.bottle.team.model.enumaration.Role;
import com.bottle.team.model.interfaces.NamedEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * Created by PC on 09/10/2016.
 */
public class User extends Agent implements Person, NamedEntity, Cloneable {
    @Property(name = "firstName")
    private String firstName;
    @Property(name = "lastName")
    private String lastName;
    @Property(name = "password")
    private String password;
    @Property(name = "role")
    private Role role;
    @Property(name = "provider")
    private Provider provider;

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }


    @Override
    public String toString() {
        return getEmail() + " " + getPassword() + " " + getRole();
    }

    @Override
    public User clone() throws CloneNotSupportedException {
        User user = (User)super.clone();
        user.setFirstName(getFirstName());
        user.setLastName(getLastName());
        user.setRole(getRole());
        user.setProvider(getProvider());
        user.setPassword(getPassword());
        return user;
    }

    public User cloneWithoutPassword() throws CloneNotSupportedException {
        User user = this.clone();
        user.setPassword("");
        return user;
    }
}
