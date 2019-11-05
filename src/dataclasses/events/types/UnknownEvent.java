package dataclasses.events.types;

import dataclasses.events.Event;

public class UnknownEvent extends Event {

    public UnknownEvent(String eventObj, String eventName) throws NoSuchFieldException {
        super(eventObj, eventName);
    }
}
