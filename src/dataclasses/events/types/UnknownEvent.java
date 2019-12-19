package dataclasses.events.types;

import dataclasses.events.Event;

public class UnknownEvent extends Event {
    public UnknownEvent(){}

    public UnknownEvent(String error){
        setEvent(error);
    }
}
