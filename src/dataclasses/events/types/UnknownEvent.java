package dataclasses.events.types;

import dataclasses.events.Event;

public class UnknownEvent extends Event {

    public UnknownEvent(String eventStr, String eventName) {
        super(eventStr, eventName);
    }
}
