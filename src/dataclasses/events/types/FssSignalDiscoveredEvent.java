package dataclasses.events.types;

import dataclasses.events.Event;

public class FssSignalDiscoveredEvent extends Event {
    public FssSignalDiscoveredEvent(String eventObj, String eventName) throws NoSuchFieldException {
        super(eventObj, eventName);
    }
}
