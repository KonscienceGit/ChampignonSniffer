package dataclasses.events.types;

import dataclasses.events.Event;

public class FuelScoopEvent extends Event {
    public FuelScoopEvent(String eventObj, String eventName) throws NoSuchFieldException {
        super(eventObj, eventName);
    }
}
