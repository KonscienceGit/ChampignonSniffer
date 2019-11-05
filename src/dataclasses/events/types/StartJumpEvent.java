package dataclasses.events.types;

import dataclasses.events.Event;

public class StartJumpEvent extends Event {
    public StartJumpEvent(String eventObj, String eventName) throws NoSuchFieldException {
        super(eventObj, eventName);
    }
}
