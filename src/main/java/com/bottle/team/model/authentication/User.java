package com.bottle.team.model.authentication;

import com.bottle.team.lucene.annotations.Boost;
import com.bottle.team.lucene.annotations.Field;
import com.bottle.team.lucene.enumerations.Analyze;
import com.bottle.team.model.enumaration.Provider;
import com.bottle.team.model.enumaration.Role;
import com.bottle.team.model.interfaces.NamedEntity;
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
    @Field(store = org.apache.lucene.document.Field.Store.YES, analyze = Analyze.NO)
    @Boost(4.0f)
    private String firstName;
    @NotEmpty
    @Property(name = "lastName")
    @Field(store = org.apache.lucene.document.Field.Store.YES, analyze = Analyze.NO)
    @Boost(3.0f)
    private String lastName;
    @Length(min = 6)
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
