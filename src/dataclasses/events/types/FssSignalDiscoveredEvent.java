package dataclasses.events.types;

import dataclasses.events.Event;

public class FssSignalDiscoveredEvent extends Event {
    public FssSignalDiscoveredEvent(String eventStr, String eventName) throws NoSuchFieldException {
        super(eventStr, eventName);
    }
}
