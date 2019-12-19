package dataclasses.events.types.fuel;

import com.fasterxml.jackson.annotation.JsonProperty;
import dataclasses.GameSession;
import dataclasses.events.Event;

public class FuelScoopEvent extends Event {

    private float scooped;
    private float total;

    @Override
    public void updateContext(GameSession gameSession) {
        gameSession.getFuelStatus().setFuelLevel(total);
//        this.setNeedUpdateGUI(true);
    }

    @JsonProperty("Scooped")
    public float getScooped() {
        return scooped;
    }

    @JsonProperty("Scooped")
    public void setScooped(float scooped) {
        this.scooped = scooped;
    }

    @JsonProperty("Total")
    public float getTotal() {
        return total;
    }

    @JsonProperty("Total")
    public void setTotal(float total) {
        this.total = total;
    }
}
