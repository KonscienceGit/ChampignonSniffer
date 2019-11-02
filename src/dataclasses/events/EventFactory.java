package dataclasses.events;

import dataclasses.events.types.UnknownEvent;
import tools.JsonParser;

public abstract class EventFactory {

    public static Event getEvent(String eventStr){
        String eventName = JsonParser.getStrContentOf("event", eventStr);

        switch (eventName){
            case "LoadGame":
                break;
            case "Loadout":
                break;
            case "Location":
                break;
            case "Scan":
                break;
            case "FSDTarget":
                break;
            case "StartJump": //FSD charging
                break;
            case "FSDJump": //Jump
                break;
            case "FSSSignalDiscovered":
                break;
            case "FSSDiscoveryScan":
                break;
            case "FuelScoop":
                break;
            default:
                return new UnknownEvent(eventStr, eventName);
                //System.out.println("Unknown event: ");
                //System.out.println(line);
        }//end switch case
    }
}
