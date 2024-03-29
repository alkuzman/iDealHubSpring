package com.bottle.team.model.ideas;

import com.bottle.team.model.BaseEntityImpl;
import com.bottle.team.model.authentication.User;
import com.bottle.team.model.interfaces.NamedEntity;
import com.bottle.team.model.sharing.Sharable;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by PC on 09/10/2016.
 */
public class Idea extends BaseEntityImpl implements NamedEntity, Sharable {
    @Property(name = "title")
    private String title;
    @Relationship(type = "PROBLEM")
    private Problem problem;
    @Relationship(type = "OWNER")
    private User owner;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public String constructName() {
        return title;
    }

    @Override
    public String toString() {
        return super.toString() + "Idea{" +
                "title='" + title + '\'' +
                ", problem=" + problem +
                ", owner=" + owner +
                '}';
    }
}
