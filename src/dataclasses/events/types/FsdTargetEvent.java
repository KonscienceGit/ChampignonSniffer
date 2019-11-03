package dataclasses.events.types;

import dataclasses.events.Event;

public class FsdTargetEvent extends Event{
    public FsdTargetEvent(String eventStr, String eventName) throws NoSuchFieldException {
        super(eventStr, eventName);
    }
}
