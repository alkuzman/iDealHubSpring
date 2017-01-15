package com.bottle.team.model.comments;

import com.bottle.team.lucene.annotations.Boost;
import com.bottle.team.lucene.annotations.Field;
import com.bottle.team.model.BaseEntityImpl;
import org.hibernate.validator.constraints.NotEmpty;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by PC on 23/10/2016.
 */
@NodeEntity
public class Comment extends BaseEntityImpl implements Commentable {
    @Relationship(type = "COMMENTABLE")
    private Commentable commentable;

    @NotEmpty
    @Property(name = "text")
    @Field(store = org.apache.lucene.document.Field.Store.YES)
    @Boost(1f)
    private String text;

    public Commentable getCommentable() {
        return commentable;
    }

    public void setCommentable(Commentable commentable) {
        this.commentable = commentable;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
