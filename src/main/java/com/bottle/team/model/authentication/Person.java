package com.bottle.team.model.authentication;

import com.bottle.team.model.interfaces.BaseEntity;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by Viki on 10/14/2016.
 */
@JsonSubTypes({
        @JsonSubTypes.Type(value = User.class),
        @JsonSubTypes.Type(value = Member.class)
})
public interface Person extends BaseEntity {
}
