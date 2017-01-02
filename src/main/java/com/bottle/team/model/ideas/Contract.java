package com.bottle.team.model.ideas;

import com.bottle.team.model.BaseEntityImpl;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by Viki on 10/14/2016.
 */
@NodeEntity
public class Contract extends BaseEntityImpl {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
