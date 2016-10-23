package com.bottle.team.model.ideas;

import com.bottle.team.model.BaseEntityImpl;

/**
 * Created by Viki on 10/14/2016.
 */
public class Contract extends BaseEntityImpl {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
