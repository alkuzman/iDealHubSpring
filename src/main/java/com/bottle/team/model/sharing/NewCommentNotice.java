package com.bottle.team.model.sharing;

import com.bottle.team.model.comments.Comment;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import javax.validation.constraints.NotEmpty;

/**
 * Created by AKuzmanoski on 23/10/2016.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 23/10/2016
 */
@NodeEntity
public class NewCommentNotice extends AbstractNotice {
    @NotEmpty
    @Relationship(type = "COMMENT")
    private Comment comment;

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
