package com.bottle.team.model.sharing;

import com.bottle.team.model.authentication.Member;
import com.bottle.team.model.authentication.User;
import com.bottle.team.model.ideas.Idea;
import com.bottle.team.model.ideas.Problem;
import com.bottle.team.model.interfaces.BaseEntity;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by PC on 23/10/2016.
 */
@NodeEntity
@JsonSubTypes({
        @JsonSubTypes.Type(value = Idea.class),
        @JsonSubTypes.Type(value = Problem.class)
})
public interface Shareable extends BaseEntity {
}
