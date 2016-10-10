package com.bottle.team.model;

import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by PC on 09/10/2016.
 */

public class Idea extends BaseEntity {
    String title;
    String problem;
    String solution;
    @Relationship(type = "OWNER")
    Person owner;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
