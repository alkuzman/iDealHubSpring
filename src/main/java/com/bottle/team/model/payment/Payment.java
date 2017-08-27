package com.bottle.team.model.payment;

import com.bottle.team.model.interfaces.BaseEntity;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
@JsonSubTypes({
        @JsonSubTypes.Type(value = Price.class),
        @JsonSubTypes.Type(value = Contract.class)
})
public interface Payment extends BaseEntity {
}
