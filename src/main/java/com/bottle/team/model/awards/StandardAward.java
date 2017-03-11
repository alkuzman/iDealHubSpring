package com.bottle.team.model.awards;

import com.bottle.team.lucene.annotations.Boost;
import com.bottle.team.lucene.annotations.Field;
import com.bottle.team.lucene.annotations.IndexedEmbedded;
import com.bottle.team.model.BaseEntityImpl;
import com.bottle.team.model.awards.badges.Badge;
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
public class StandardAward<T extends Badge> extends BaseEntityImpl implements Award<T> {
    @Relationship(type = "BADGE")
    @IndexedEmbedded
    @Boost(2f)
    private T badge;
    @Property(name = "description")
    @Field(store = org.apache.lucene.document.Field.Store.YES)
    @Boost(1.0f)
    private String description;

    public T getBadge() {
        return badge;
    }

    public void setBadge(T badge) {
        this.badge = badge;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }
}
