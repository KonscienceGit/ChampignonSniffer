package dataclasses.events;

import dataclasses.EliteDate;

import static tools.JsonParser.getObjectSubstring;
import static tools.JsonParser.getStrContentOf;

public abstract class Event {
    private final String _eventObj;
    private final String _eventName;
    private final EliteDate _date;

    public Event(String eventObj, String eventName) throws NoSuchFieldException {
        _eventObj = eventObj;
        _eventName = eventName;
        String eventTimestamp = getStrContentOf("timestamp", eventObj);
        _date = new EliteDate(eventTimestamp);
    }

    public boolean isOfType(String eventType){
        return _eventName.equals(eventType);
    }

    public String getEventObject(){
        return _eventObj;
    }

    public String getEventName(){
        return _eventName;
    }

    public EliteDate getDate(){
        return _date;
    }
}
