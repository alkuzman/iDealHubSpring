package com.bottle.team.model.helpers;

import com.bottle.team.model.BaseEntityImpl;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * Created by Viki on 2/21/2017.
 */
@NodeEntity
public class Currency extends BaseEntityImpl {
    @Property(name = "value")
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
