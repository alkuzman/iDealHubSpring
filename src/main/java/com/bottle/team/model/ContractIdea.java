package com.bottle.team.model;

import org.neo4j.ogm.annotation.Property;

/**
 * Created by Viki on 10/14/2016.
 */
public class ContractIdea extends Idea {
    @Property(name = "sneakPeek")
    String sneakPeek;

    public String getSneakPeek() {
        return sneakPeek;
    }

    public void setSneakPeek(String sneakPeek) {
        this.sneakPeek = sneakPeek;
    }
}
