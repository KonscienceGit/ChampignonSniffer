package dataclasses.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dataclasses.events.types.*;
import dataclasses.events.types.fuel.FuelScoopEvent;
import dataclasses.events.types.navigation.FsdJumpEvent;
import dataclasses.events.types.navigation.FsdTargetEvent;
import dataclasses.events.types.navigation.LocationEvent;
import dataclasses.events.types.navigation.StartJumpEvent;
import dataclasses.events.types.warnings.HeatWarning;
import tools.JacksonMapper;
import tools.JsonParser;
import tools.TerminalLogger;

import static tools.Constants.EventType.*;

public abstract class EventFactory {

    public static Event getEvent(String eventObj){
        String eventName;
        try {
            eventName = JsonParser.getStrContentOf("event", eventObj);
            ObjectMapper jksMapper = JacksonMapper.getMapper();
            //jksMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            switch (eventName){
                case FILE_HEADER:
                    return jksMapper.readValue(eventObj, FileheaderEvent.class);

                case LOAD_GAME:
                    return jksMapper.readValue(eventObj, LoadGameEvent.class);

                case LOADOUT:
                    return jksMapper.readValue(eventObj, LoadoutEvent.class);

                case LOCATION:
                    return jksMapper.readValue(eventObj, LocationEvent.class);

                case SCAN:
                    return jksMapper.readValue(eventObj, BodyScanEvent.class);

                case FSD_TARGET:
                    return jksMapper.readValue(eventObj, FsdTargetEvent.class);

                case START_JUMP:
                    return jksMapper.readValue(eventObj, StartJumpEvent.class);

                case FSD_JUMP:
                    return jksMapper.readValue(eventObj, FsdJumpEvent.class);

                case FSS_SIGNAL_DISCOVERED:
                    return jksMapper.readValue(eventObj, FssSignalDiscoveredEvent.class);

                case FSS_DISCOVERY_SCAN:
                    return jksMapper.readValue(eventObj, FssDiscoveryScanEvent.class);

                case FUEL_SCOOP:
                    return jksMapper.readValue(eventObj, FuelScoopEvent.class);

                case HEAT_WARNING:
                    return jksMapper.readValue(eventObj, HeatWarning.class);

                default:
                    //return jksMapper.readValue(eventObj, UnknownEvent.class);
                    return new UnknownEvent(eventName);
            }
        } catch (NoSuchFieldException | JsonProcessingException e) {
            TerminalLogger.logStackTrace(e);
            return new UnknownEvent("ParsingError");
      }
    }
}
