package dataclasses.events.types.navigation;

import com.fasterxml.jackson.annotation.JsonProperty;
import dataclasses.GameSession;
import dataclasses.events.Event;

/**
 * Event occur when a remote star system is selected. Two cases are possible:
 * -You compute a new route, once the plotting finish, the event is fired.
 * -You initiate a H-Jump toward system n°1, the FsdTargetEvent will tell about this system.
 * -The jumpdrive finished loading, just before entering witchspace, another FsdTargetEvent may fire,
 *  instead telling about the final destination system.
 */
public class FsdTargetEvent extends Event{

    private String systemName;
    private long systemAddress;
    private int remainingJumpsInRoute;

    @Override
    public void updateContext(GameSession gameSession) {
//        gameSession
//        this.setNeedUpdateGUI(true);
    }

    @JsonProperty("Name")
    public String getSystemName() {
        return systemName;
    }

    @JsonProperty("Name")
    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    @JsonProperty("SystemAddress")
    public long getSystemAddress() {
        return systemAddress;
    }

    @JsonProperty("SystemAddress")
    public void setSystemAddress(long systemAddress) {
        this.systemAddress = systemAddress;
    }

    @JsonProperty("RemainingJumpsInRoute")
    public int getRemainingJumpsInRoute() {
        return remainingJumpsInRoute;
    }

    @JsonProperty("RemainingJumpsInRoute")
    public void setRemainingJumpsInRoute(int remainingJumpsInRoute) {
        this.remainingJumpsInRoute = remainingJumpsInRoute;
    }
}
