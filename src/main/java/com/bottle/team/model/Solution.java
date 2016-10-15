package com.bottle.team.model;

import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by Viki on 10/14/2016.
 */
public class Solution extends BaseEntity {
    @Property(name = "solution")
    String solution;

    @Relationship(type = "IDEA")
    ContractIdea idea;
}
