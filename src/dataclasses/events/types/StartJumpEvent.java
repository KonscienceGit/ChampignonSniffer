package dataclasses.events.types;

import dataclasses.events.Event;

public class StartJumpEvent extends Event {
    public StartJumpEvent(String eventStr, String eventName) throws NoSuchFieldException {
        super(eventStr, eventName);
    }
}
