package dataclasses.events.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import dataclasses.GameSession;
import dataclasses.events.Event;

public class FileheaderEvent extends Event {
    private int filePart;
    private String language;
    private String gameversion;
    private String build;

    @Override
    public void updateContext(GameSession gameSession) {
        gameSession.setCurrentFileHeader(this);
        this.setNeedUpdateGUI(true);
    }

    @JsonProperty("part")
    public int getFilePart() {
        return filePart;
    }

    @JsonProperty("part")
    public void setFilePart(int filePart) {
        this.filePart = filePart;
    }

    @JsonProperty("language")
    public String getLanguage() {
        return language;
    }

    @JsonProperty("language")
    public void setLanguage(String language) {
        this.language = language;
    }

    @JsonProperty("gameversion")
    public String getGameversion() {
        return gameversion;
    }

    @JsonProperty("gameversion")
    public void setGameversion(String gameversion) {
        this.gameversion = gameversion;
    }

    @JsonProperty("build")
    public String getBuild() {
        return build;
    }

    @JsonProperty("build")
    public void setBuild(String build) {
        this.build = build;
    }
}
