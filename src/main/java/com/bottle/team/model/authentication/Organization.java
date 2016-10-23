package com.bottle.team.model.authentication;

import com.bottle.team.model.interfaces.NamedEntity;

/**
 * Created by PC on 09/10/2016.
 */
public class Organization extends Agent implements NamedEntity {

    @Override
    public String constructName() {
        return getName();
    }
}
