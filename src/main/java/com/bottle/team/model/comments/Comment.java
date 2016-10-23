package com.bottle.team.model.comments;

import com.bottle.team.model.BaseEntityImpl;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by PC on 23/10/2016.
 */
public class Comment extends BaseEntityImpl implements Commentable {
    @Relationship(type = "COMMENTABLE")
    private Commentable commentable;

    public Commentable getCommentable() {
        return commentable;
    }

    public void setCommentable(Commentable commentable) {
        this.commentable = commentable;
    }
}
