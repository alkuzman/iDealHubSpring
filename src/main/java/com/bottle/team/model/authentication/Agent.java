package com.bottle.team.model.authentication;

import com.bottle.team.model.BaseEntityImpl;
import com.sun.istack.internal.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * Created by PC on 09/10/2016.
 */
@NodeEntity
public class Agent extends BaseEntityImpl implements Cloneable {
    @Email
    @Property(name = "email")
    private String email;
    @Property(name = "telephone")
    private String telephone;
    @Property(name = "profilePicture")
    private String profilePicture;
    @Property(name = "coverPicture")
    private String coverPicture;

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

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getCoverPicture() {
        return coverPicture;
    }

    public void setCoverPicture(String coverPicture) {
        this.coverPicture = coverPicture;
    }

    @Override
    public Agent clone() throws CloneNotSupportedException {
        Agent agent = (Agent)super.clone();
        agent.setEmail(getEmail());
        agent.setTelephone(getTelephone());
        agent.setProfilePicture(getProfilePicture());
        agent.setCoverPicture(getCoverPicture());
        return agent;
    }
}
