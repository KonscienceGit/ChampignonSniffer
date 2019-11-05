package dataclasses.events.types;

import dataclasses.events.Event;

public class FsdTargetEvent extends Event{
    public FsdTargetEvent(String eventObj, String eventName) throws NoSuchFieldException {
        super(eventObj, eventName);
    }
}
