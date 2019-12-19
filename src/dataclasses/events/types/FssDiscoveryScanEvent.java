package dataclasses.events.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import dataclasses.GameSession;
import dataclasses.events.Event;

public class FssDiscoveryScanEvent extends Event {

    private float progress;
    private int bodyCount;
    private int nonBodyCount;
    private String systemName;
    private long systemAddress;

    @Override
    public void updateContext(GameSession gameSession) {
        gameSession.getCurrentStarSystem().initializeWithFSSD(this);
        this.setNeedUpdateGUI(true);
    }

    @JsonProperty("Progress")
    public float getProgress() {
        return progress;
    }

    @JsonProperty("Progress")
    public void setProgress(float progress) {
        this.progress = progress;
    }

    @JsonProperty("BodyCount")
    public int getBodyCount() {
        return bodyCount;
    }

    @JsonProperty("BodyCount")
    public void setBodyCount(int bodyCount) {
        this.bodyCount = bodyCount;
    }

    @JsonProperty("NonBodyCount")
    public int getNonBodyCount() {
        return nonBodyCount;
    }

    @JsonProperty("NonBodyCount")
    public void setNonBodyCount(int nonBodyCount) {
        this.nonBodyCount = nonBodyCount;
    }

    @JsonProperty("SystemName")
    public String getSystemName() {
        return systemName;
    }

    @JsonProperty("SystemName")
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
}
