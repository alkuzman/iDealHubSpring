package com.bottle.team.model.awards.bedges;

import com.bottle.team.lucene.annotations.Boost;
import com.bottle.team.lucene.annotations.Field;
import com.bottle.team.lucene.annotations.IndexedEmbedded;
import com.bottle.team.model.BaseEntityImpl;
import org.hibernate.validator.constraints.NotEmpty;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by AKuzmanoski on 05/03/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 05/03/2017
 */
@NodeEntity
public abstract class AbstractBadge<F, T extends Badge> extends BaseEntityImpl implements Badge<F, T> {
    @NotEmpty
    @Property(name = "name")
    @Field(store = org.apache.lucene.document.Field.Store.YES)
    @Boost(3.0f)
    private String name;
    @Property(name = "description")
    @Field(store = org.apache.lucene.document.Field.Store.YES)
    @Boost(1.0f)
    private String description;
    @Relationship(type = "NEXT")
    @IndexedEmbedded
    @Boost(0.5f)
    private T next;

    @Override
    public T getNext() {
        return next;
    }

    @Override
    public void setNext(T next) {
        this.next = next;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
