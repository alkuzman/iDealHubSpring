package com.bottle.team.model;

import com.bottle.team.model.interfaces.Questioner;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by Viki on 10/14/2016.
 */
public class Contract extends BaseEntity {
    @Relationship(type = "CONTRACT_IDEA")
    ContractIdea idea;

    @Relationship(type = "BUYER")
    Questioner buyer;
}
