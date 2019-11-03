package dataclasses.events.types;

import dataclasses.events.Event;

public class LocationEvent extends Event {
    public LocationEvent(String eventStr, String eventName) throws NoSuchFieldException {
        super(eventStr, eventName);
    }
}
