package com.bottle.team.model.ideas;

import com.bottle.team.lucene.annotations.Boost;
import com.bottle.team.lucene.annotations.Field;
import com.bottle.team.model.BaseEntityImpl;
import org.hibernate.validator.constraints.NotEmpty;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * Created by AKuzmanoski on 06/03/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 06/03/2017
 */
@NodeEntity
public class Keyword extends BaseEntityImpl {
    @NotEmpty
    @Property(name = "phrase")
    @Field(store = org.apache.lucene.document.Field.Store.YES)
    @Boost(4.0f)
    private String phrase;
    @NotEmpty
    @Property(name = "score")
    private Double score;

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
