package com.bottle.team.events;

import org.neo4j.ogm.session.event.Event;

public class ModificationEvent {

    private Event source;

    public ModificationEvent(Event source) {
        this.source = source;
    }

    public Event getSource() {
        return source;
    }

}
