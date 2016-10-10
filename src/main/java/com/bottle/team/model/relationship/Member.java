package com.bottle.team.model.relationship;

import com.bottle.team.model.Organization;
import com.bottle.team.model.Person;
import com.bottle.team.model.enumaration.MemberRole;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * Created by PC on 09/10/2016.
 */
@RelationshipEntity(type = "MEMBER")
public class Member extends BaseRelationship {
    @StartNode
    Organization organization;
    @EndNode
    Person person;
    @Property
    MemberRole role;
}
