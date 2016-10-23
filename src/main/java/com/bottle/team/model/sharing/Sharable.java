package com.bottle.team.model.sharing;

import com.bottle.team.model.authentication.Member;
import com.bottle.team.model.authentication.User;
import com.bottle.team.model.ideas.Idea;
import com.bottle.team.model.ideas.Problem;
import com.bottle.team.model.interfaces.BaseEntity;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by PC on 23/10/2016.
 */
@JsonSubTypes({
        @JsonSubTypes.Type(value = Idea.class),
        @JsonSubTypes.Type(value = Problem.class)
})
public interface Sharable extends BaseEntity {
}
