package com.bottle.team.model.authentication;

import com.bottle.team.model.interfaces.NamedEntity;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by PC on 09/10/2016.
 */
@NodeEntity
public class Organization extends Agent implements NamedEntity {

    @Override
    public String constructName() {
        return getName();
    }
}
