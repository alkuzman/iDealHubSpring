package com.bottle.team.events;

import org.neo4j.ogm.session.event.Event;

public class PreDeleteEvent extends ModificationEvent {
    public PreDeleteEvent(Event source) {
        super(source);
    }
}
