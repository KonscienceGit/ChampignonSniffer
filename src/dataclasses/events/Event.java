package dataclasses.events;

import dataclasses.EliteDate;
import tools.JsonParser;

import static tools.JsonParser.getStrContentOf;

public abstract class Event {
    private final String _eventStr;
    private final String _eventName;
    private final String _eventTimestamp;
    private final EliteDate _date;

    public Event(String eventStr, String eventName) throws NoSuchFieldException {
        _eventStr = eventStr;
        _eventName = eventName;
        _eventTimestamp = getStrContentOf("timestamp", eventStr);
        _date = new EliteDate(_eventTimestamp);
    }

    public boolean isOfType(String eventType){
        //noinspection StringEquality
        return _eventName == eventType;
    }

    public String getEventString(){
        return _eventStr;
    }

    public String getEventName(){
        return _eventName;
    }

    public EliteDate getDate(){
        return _date;
    }
}
