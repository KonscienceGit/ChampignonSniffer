package dataclasses.events.types;

import dataclasses.events.Event;

public class ScanEvent extends Event {
    public ScanEvent(String eventStr, String eventName) throws NoSuchFieldException {
        super(eventStr, eventName);
    }
}
