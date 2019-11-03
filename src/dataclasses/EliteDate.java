package dataclasses;

public class EliteDate {
    private short _year;
    private byte _month;
    private byte _day;
    private byte _hours;
    private byte _minutes;
    private byte _seconds;

    public EliteDate(String timestamp){
        try{
            _year = Short.parseShort(timestamp.substring(0,4));
            _month = Byte.parseByte(timestamp.substring(5,7));
            _day = Byte.parseByte(timestamp.substring(8,10));
            _hours = Byte.parseByte(timestamp.substring(11,13));
            _minutes = Byte.parseByte(timestamp.substring(14,16));
            _seconds = Byte.parseByte(timestamp.substring(17,19));
        } catch (StringIndexOutOfBoundsException e){
            throw new StringIndexOutOfBoundsException("Invalid timestamp: " + timestamp);
        }
    }

    public EliteDate(short year, byte month, byte day, byte hours, byte minutes, byte seconds){
        _year = year;
        _month = month;
        _day = day;
        _hours = hours;
        _minutes = minutes;
        _seconds = seconds;
    }

    public short getYear() {
        return _year;
    }

    public byte getMonth() {
        return _month;
    }

    public byte getDay() {
        return _day;
    }

    public byte getHours() {
        return _hours;
    }

    public byte getMinutes() {
        return _minutes;
    }

    public byte getSeconds() {
        return _seconds;
    }
}
