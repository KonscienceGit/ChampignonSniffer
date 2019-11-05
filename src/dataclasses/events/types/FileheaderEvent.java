package dataclasses.events.types;

import dataclasses.events.Event;
import tools.JsonParser;

public class FileheaderEvent extends Event {
    private int _filePart;

    public FileheaderEvent(String eventObj, String eventName) throws NoSuchFieldException {
        super(eventObj, eventName);
        _filePart = JsonParser.getIntContentOf("part", eventObj);
    }

    public int getFilePartNumber(){
        return _filePart;
    }
}
