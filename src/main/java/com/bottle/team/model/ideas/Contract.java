package com.bottle.team.model.ideas;

import com.bottle.team.lucene.annotations.Boost;
import com.bottle.team.lucene.annotations.Field;
import com.bottle.team.model.BaseEntityImpl;
import com.bottle.team.model.comments.Comment;
import org.hibernate.validator.constraints.NotEmpty;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * Created by Viki on 10/14/2016.
 */
@NodeEntity
public class Contract extends BaseEntityImpl {
    @NotEmpty
    @Property(name = "text")
    @Field(store = org.apache.lucene.document.Field.Store.YES)
    @Boost(1f)
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
