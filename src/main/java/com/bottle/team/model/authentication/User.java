package com.bottle.team.model.authentication;

import com.bottle.team.lucene.annotations.Boost;
import com.bottle.team.lucene.annotations.Field;
import com.bottle.team.lucene.enumerations.Analyze;
import com.bottle.team.model.enumaration.Provider;
import com.bottle.team.model.enumaration.Role;
import com.bottle.team.model.interfaces.NamedEntity;
import org.hibernate.validator.constraints.Length;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import javax.validation.constraints.NotEmpty;

/**
 * Created by PC on 09/10/2016.
 */
@NodeEntity
public class User extends Agent implements Person, NamedEntity, Cloneable {
    @NotEmpty
    @Property(name = "firstName")
    @Field(store = org.apache.lucene.document.Field.Store.YES, analyze = Analyze.NO)
    @Boost(4.0f)
    private String firstName;
    @NotEmpty
    @Property(name = "lastName")
    @Field(store = org.apache.lucene.document.Field.Store.YES, analyze = Analyze.NO)
    @Boost(3.0f)
    private String lastName;
    @NotEmpty
    @Property(name = "country")
    private String country;
    @Length(min = 6)
    @Property(name = "password")
    private String password;
    @Property(name = "role")
    private Role role;
    @Property(name = "provider")
    private Provider provider;
    @Property(name = "activationCode")
    private String activationCode;
    @Property(name = "securityProfileInitialized")
    private boolean securityProfileInitialized;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        setNewName();
    }

    private void setNewName() {
        StringBuilder stringBuilder = new StringBuilder();
        if (firstName != null) {
            stringBuilder.append(firstName);
            stringBuilder.append(" ");
        }
        if (lastName != null)
        stringBuilder.append(lastName);
        setName(stringBuilder.toString());
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        setNewName();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public boolean isSecurityProfileInitialized() {
        return securityProfileInitialized;
    }

    public void setSecurityProfileInitialized(boolean securityProfileInitialized) {
        this.securityProfileInitialized = securityProfileInitialized;
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
        user.setCountry(getCountry());
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
