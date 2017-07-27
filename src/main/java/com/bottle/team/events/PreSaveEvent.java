package com.bottle.team.events;

import org.neo4j.ogm.session.event.Event;

public class PreSaveEvent extends ModificationEvent {

    public PreSaveEvent(Event source) {
        super(source);
    }
}
