package dataclasses.events;

import dataclasses.EliteDate;
import tools.JsonParser;

public abstract class Event {
    private final String _eventStr;
    private final String _eventName;
    private final String _eventTimestamp;
    private final EliteDate _date;

    public Event(String eventStr, String eventName){
        _eventStr = eventStr;
        _eventName = eventName;
        _eventTimestamp = JsonParser.getStrContentOf("timestamp", eventStr);
        _date = new EliteDate(eventStr);
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
