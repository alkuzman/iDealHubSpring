package com.bottle.team.model.ideas;

import com.bottle.team.lucene.annotations.Boost;
import com.bottle.team.lucene.annotations.Field;
import com.bottle.team.lucene.annotations.IndexedEmbedded;
import com.bottle.team.model.BaseEntityImpl;
import org.hibernate.validator.constraints.NotEmpty;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import javax.validation.constraints.NotNull;

/**
 * Created by Viki on 10/14/2016.
 */
@NodeEntity
public class Solution extends BaseEntityImpl {
    @NotEmpty
    @Property(name = "text")
    @Field(store = org.apache.lucene.document.Field.Store.YES)
    @Boost(1.0f)
    private String text;

    @NotNull
    @Relationship(type = "IDEA")
    @IndexedEmbedded
    @Boost(1f)
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
