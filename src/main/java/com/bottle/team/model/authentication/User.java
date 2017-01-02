package com.bottle.team.model.authentication;

import com.bottle.team.model.enumaration.Provider;
import com.bottle.team.model.enumaration.Role;
import com.bottle.team.model.interfaces.NamedEntity;
import com.sun.istack.internal.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * Created by PC on 09/10/2016.
 */
@NodeEntity
public class User extends Agent implements Person, NamedEntity, Cloneable {
    @NotEmpty
    @Property(name = "firstName")
    private String firstName;
    @NotEmpty
    @Property(name = "lastName")
    private String lastName;
    @Length(min = 8)
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

    @Override
    public User user() {
        return this;
    }
}
