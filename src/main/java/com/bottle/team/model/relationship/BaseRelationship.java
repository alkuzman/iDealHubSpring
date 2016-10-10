package com.bottle.team.model.relationship;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 * Created by PC on 09/10/2016.
 */
public class BaseRelationship {
    @GraphId Long id;
}
