package com.bottle.team.model.interfaces;

import com.bottle.team.model.Member;
import com.bottle.team.model.Person;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by Viki on 10/14/2016.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Person.class),
        @JsonSubTypes.Type(value = Member.class)
})
public interface Questioner {
}
