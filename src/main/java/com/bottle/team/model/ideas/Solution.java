package com.bottle.team.model.ideas;

import com.bottle.team.model.BaseEntityImpl;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by Viki on 10/14/2016.
 */
public class Solution extends BaseEntityImpl {
    @Property(name = "text")
    private String text;

    @Relationship(type = "IDEA")
    private Idea idea;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Idea getIdea() {
        return idea;
    }

    public void setIdea(Idea idea) {
        this.idea = idea;
    }
}
