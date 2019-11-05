package dataclasses.events.types;

import dataclasses.events.Event;

public class LocationEvent extends Event {
    public LocationEvent(String eventObj, String eventName) throws NoSuchFieldException {
        super(eventObj, eventName);
    }
}
