package com.bottle.team.model.ideas;

import com.bottle.team.model.interfaces.BaseEntity;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
@JsonSubTypes({
        @JsonSubTypes.Type(value = AbstractDigitalGoods.class),
        @JsonSubTypes.Type(value = Idea.class),
        @JsonSubTypes.Type(value = Evaluation.class)
})
public interface DigitalGoods extends BaseEntity {
}
