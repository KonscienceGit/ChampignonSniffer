package dataclasses.events.types;

import dataclasses.events.Event;
import tools.JsonParser;

public class FileheaderEvent extends Event {
    private int _filePart;

    public FileheaderEvent(String eventStr, String eventName) throws NoSuchFieldException {
        super(eventStr, eventName);
        _filePart = JsonParser.getIntContentOf("part", eventStr);
    }

    public int getFilePartNumber(){
        return _filePart;
    }
}
