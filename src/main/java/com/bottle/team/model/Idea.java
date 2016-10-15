package com.bottle.team.model;

import com.bottle.team.model.interfaces.NamedEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by PC on 09/10/2016.
 */

public class Idea extends BaseEntity implements NamedEntity {
    @Property(name = "title")
    String title;
    @Relationship(type = "PROBLEM")
    Problem problem;
    @Relationship(type = "OWNER")
    Person owner;

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

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @Override
    public String constructName() {
        return title;
    }
}
