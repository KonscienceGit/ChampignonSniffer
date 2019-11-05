package dataclasses.events.types;

import dataclasses.events.Event;

public class FsdJumpEvent extends Event {
    public FsdJumpEvent(String eventObj, String eventName) throws NoSuchFieldException {
        super(eventObj, eventName);
    }
}
