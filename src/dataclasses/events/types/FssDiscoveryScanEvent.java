package dataclasses.events.types;

import dataclasses.events.Event;

public class FssDiscoveryScanEvent extends Event {
    public FssDiscoveryScanEvent(String eventStr, String eventName) throws NoSuchFieldException {
        super(eventStr, eventName);
    }
}
