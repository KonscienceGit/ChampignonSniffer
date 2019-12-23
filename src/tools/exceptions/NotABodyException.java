package tools.exceptions;

import dataclasses.events.types.BodyScanEvent;

public class NotABodyException extends Exception {
    public NotABodyException(String msg, BodyScanEvent event){
        super(msg);
    }

    public NotABodyException(Throwable t, BodyScanEvent event){
        super(t);
    }

    public NotABodyException(String msg, Throwable t, BodyScanEvent event){
        super(msg, t);
    }
}