package dataclasses.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import dataclasses.GameSession;
import gui.ChampignonScreenJFrame;

public abstract class Event {
    private String timestamp;
    private String event;
    private boolean needUpdateGUI = false;

    public Event(){}

    public void updateContext(GameSession gameSession){}

    public void updateGUI(ChampignonScreenJFrame champScr){}

    @JsonProperty("timestamp")
    public String getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty("event")
    public String getEvent() {
        return event;
    }

    @JsonProperty("event")
    public void setEvent(String event) {
        this.event = event;
    }

    public boolean isNeedUpdateGUI() {
        return needUpdateGUI;
    }

    public void setNeedUpdateGUI(boolean needUpdateGUI) {
        this.needUpdateGUI = needUpdateGUI;
    }
}
