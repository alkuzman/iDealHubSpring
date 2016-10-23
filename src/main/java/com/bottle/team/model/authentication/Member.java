package com.bottle.team.model.authentication;

import com.bottle.team.model.BaseEntityImpl;
import com.bottle.team.model.enumaration.MemberRole;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by Viki on 10/14/2016.
 */
public class Member extends BaseEntityImpl implements Person {
    @Relationship(type = "ORGANIZATION")
    private Organization organization;
    @Relationship(type = "PERSON")
    private User user;
    @Property(name = "role")
    private MemberRole role;

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MemberRole getRole() {
        return role;
    }

    public void setRole(MemberRole role) {
        this.role = role;
    }
}
