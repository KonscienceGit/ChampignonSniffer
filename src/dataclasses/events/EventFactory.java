package dataclasses.events;

import dataclasses.events.types.*;
import tools.JsonParser;

import static tools.Constants.EventType.*;
import static tools.JsonParser.getObjectSubstring;

public abstract class EventFactory {

    public static Event getEvent(String eventStr){
        String eventName = "";
        String eventObj = "";
        try {
            eventObj = getObjectSubstring(eventStr, 0);
            eventName = JsonParser.getStrContentOf("event", eventObj);

            switch (eventName){
                case FILE_HEADER:
                    return new FileheaderEvent(eventObj, eventName);

                case LOAD_GAME:
                    return new LoadGameEvent(eventObj, eventName);

                case LOADOUT:
                    return new LoadoutEvent(eventObj, eventName);

                case LOCATION:
                    return new LocationEvent(eventObj, eventName);

                case SCAN:
                    return new ScanEvent(eventObj, eventName);

                case FSD_TARGET:
                    return new FsdTargetEvent(eventObj, eventName);

                case START_JUMP:
                    return new StartJumpEvent(eventObj, eventName);

                case FSD_JUMP:
                    return new FsdJumpEvent(eventObj, eventName);

                case FSS_SIGNAL_DISCOVERED:
                    return new FssSignalDiscoveredEvent(eventObj, eventName);

                case FSS_DISCOVERY_SCAN:
                    return new FssDiscoveryScanEvent(eventObj, eventName);

                case FUEL_SCOOP:
                    return new FuelScoopEvent(eventObj, eventName);

                default:
                    return new UnknownEvent(eventObj, eventName);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            try {
                return new UnknownEvent(eventObj, eventName);
            } catch (NoSuchFieldException ex) {
                ex.printStackTrace();
                return null;
            }
        }
    }
}
