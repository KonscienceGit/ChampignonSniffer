package dataclasses.events.types;

import dataclasses.events.Event;

public class ScanEvent extends Event {
    public ScanEvent(String eventObj, String eventName) throws NoSuchFieldException {
        super(eventObj, eventName);
    }
}
