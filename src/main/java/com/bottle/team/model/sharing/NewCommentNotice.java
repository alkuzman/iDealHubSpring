package com.bottle.team.model.sharing;

import com.bottle.team.model.comments.Comment;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by AKuzmanoski on 23/10/2016.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 23/10/2016
 */
@NodeEntity
public class NewCommentNotice extends Notice {
    private Comment comment;

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
