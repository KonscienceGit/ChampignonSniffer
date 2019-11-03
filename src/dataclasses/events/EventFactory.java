package dataclasses.events;

import dataclasses.events.types.*;
import tools.JsonParser;

import static tools.Constants.EventType.*;

public abstract class EventFactory {

    public static Event getEvent(String eventStr){
        String eventName = null;
        try {
            eventName = JsonParser.getStrContentOf("event", eventStr);

            switch (eventName){
                case FILE_HEADER:
                    return new FileheaderEvent(eventStr, eventName);

                case LOAD_GAME:
                    return new LoadGameEvent(eventStr, eventName);

                case LOADOUT:
                    return new LoadoutEvent(eventStr, eventName);

                case LOCATION:
                    return new LocationEvent(eventStr, eventName);

                case SCAN:
                    return new ScanEvent(eventStr, eventName);

                case FSD_TARGET:
                    return new FsdTargetEvent(eventStr, eventName);

                case START_JUMP:
                    return new StartJumpEvent(eventStr, eventName);

                case FSD_JUMP:
                    return new FsdJumpEvent(eventStr, eventName);

                case FSS_SIGNAL_DISCOVERED:
                    return new FssSignalDiscoveredEvent(eventStr, eventName);

                case FSS_DISCOVERY_SCAN:
                    return new FssDiscoveryScanEvent(eventStr, eventName);

                case FUEL_SCOOP:
                    return new FuelScoopEvent(eventStr, eventName);

                default:
                    return new UnknownEvent(eventStr, eventName);
            }

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            try {
                return new UnknownEvent(eventStr, eventName);
            } catch (NoSuchFieldException ex) {
                ex.printStackTrace();
                return null;
            }
        }
    }
}
