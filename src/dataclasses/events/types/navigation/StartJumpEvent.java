package dataclasses.events.types.navigation;

import com.fasterxml.jackson.annotation.JsonProperty;
import dataclasses.GameSession;
import dataclasses.events.Event;
import gui.ChampignonScreenJFrame;

/**
 * StarJump occur when initiating a jump (just after the loading, once you can't abort), either through hyperspace or in supercruise.
 */
public class StartJumpEvent extends Event {

    private String jumpType;
    private String starSystem;
    private long systemAddress;
    private String starClass;

    @Override
    public void updateContext(GameSession gameSession) {
        if(jumpType != null && jumpType.toLowerCase().contains("hyper")){
            gameSession.getNextStarSystem().initializeWithStrJmp(this);
            this.setNeedUpdateGUI(true);
        }
    }

    @Override
    public void updateGUI(ChampignonScreenJFrame champScr){

    }

    @JsonProperty("JumpType")
    public String getJumpType() {
        return jumpType;
    }

    @JsonProperty("JumpType")
    public void setJumpType(String jumpType) {
        this.jumpType = jumpType;
    }

    @JsonProperty("StarSystem")
    public String getStarSystem() {
        return starSystem;
    }

    @JsonProperty("StarSystem")
    public void setStarSystem(String starSystem) {
        this.starSystem = starSystem;
    }

    @JsonProperty("SystemAddress")
    public long getSystemAddress() {
        return systemAddress;
    }

    @JsonProperty("SystemAddress")
    public void setSystemAddress(long systemAddress) {
        this.systemAddress = systemAddress;
    }

    @JsonProperty("StarClass")
    public String getStarClass() {
        return starClass;
    }

    @JsonProperty("StarClass")
    public void setStarClass(String starClass) {
        this.starClass = starClass;
    }
}
