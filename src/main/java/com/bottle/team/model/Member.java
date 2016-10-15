package com.bottle.team.model;

import com.bottle.team.model.interfaces.Questioner;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by Viki on 10/14/2016.
 */
public class Member extends BaseEntity implements Questioner {
    @Relationship(type = "ORGANIZATION")
    Organization organization;
    @Relationship(type = "PERSON")
    Person person;
}
