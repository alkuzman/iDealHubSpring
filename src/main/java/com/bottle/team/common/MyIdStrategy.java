package com.bottle.team.common;

import org.neo4j.ogm.id.IdStrategy;

import java.util.UUID;

/**
 * Created by AKuzmanoski on 31/07/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 31/07/2017
 */
public class MyIdStrategy implements IdStrategy {
    public Object generateId(Object o) {
        return UUID.randomUUID().getLeastSignificantBits();
    }
}
