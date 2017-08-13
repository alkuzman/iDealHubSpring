package com.bottle.team.model.relationship;

import com.bottle.team.lucene.annotations.Field;
import com.bottle.team.lucene.enumerations.Analyze;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.springframework.data.annotation.Id;

/**
 * Created by PC on 09/10/2016.
 */
public class BaseRelationship {
    @GraphId
    @Field(analyze = Analyze.NO)
    @Id
    Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
