package dataclasses.events.types;

import dataclasses.events.Event;

public class FsdJumpEvent extends Event {
    public FsdJumpEvent(String eventStr, String eventName) throws NoSuchFieldException {
        super(eventStr, eventName);
    }
}
