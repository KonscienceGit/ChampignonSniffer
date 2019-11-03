package dataclasses.events.types;

import dataclasses.events.Event;

public class FuelScoopEvent extends Event {
    public FuelScoopEvent(String eventStr, String eventName) throws NoSuchFieldException {
        super(eventStr, eventName);
    }
}
