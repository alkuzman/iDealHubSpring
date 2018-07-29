package com.bottle.team.model.payment;

import com.bottle.team.lucene.annotations.Boost;
import com.bottle.team.lucene.annotations.Field;
import com.bottle.team.model.BaseEntityImpl;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import javax.validation.constraints.NotEmpty;

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

    @NotEmpty
    @Property(name = "title")
    @Field(store = org.apache.lucene.document.Field.Store.YES)
    @Boost(1f)
    private String title;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
