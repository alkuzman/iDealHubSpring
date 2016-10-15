package com.bottle.team.model;

import org.neo4j.ogm.annotation.Property;

/**
 * Created by Viki on 10/14/2016.
 */
public class OpenIdea extends Idea {
    @Property(name = "solution")
    String solution;

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
}
