package tools.exceptions;

/**
 * Thrown when parsing a mal-formed JSON file
 */
public class IllegalJsonFormatException extends IllegalArgumentException {
    public IllegalJsonFormatException(String msg){
        super(msg);
    }

    public IllegalJsonFormatException(Throwable t){
        super(t);
    }

    public IllegalJsonFormatException(String msg, Throwable t){
        super(msg, t);
    }
}
