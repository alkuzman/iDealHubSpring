package com.bottle.team.events;

import org.neo4j.ogm.session.event.Event;

public class PostSaveEvent extends ModificationEvent {

    public PostSaveEvent(Event source) {
        super(source);
    }
}
