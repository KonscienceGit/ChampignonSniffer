package dataclasses.events.types;

import dataclasses.events.Event;

public class UnknownEvent extends Event {

    public UnknownEvent(String eventStr, String eventName) throws NoSuchFieldException {
        super(eventStr, eventName);
    }
}
