package dataclasses.events.types;

import dataclasses.events.Event;

public class FssDiscoveryScanEvent extends Event {
    public FssDiscoveryScanEvent(String eventObj, String eventName) throws NoSuchFieldException {
        super(eventObj, eventName);
    }
}
