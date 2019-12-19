package dataclasses.events.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import dataclasses.GameSession;
import dataclasses.events.Event;
import gui.ChampignonScreenJFrame;

public class LoadGameEvent extends Event {
    private String fid;
    private String commanderName;
    private boolean isHorizonExpansionActive;
    private String ship;
    private String shipLocalised;
    private int shipID;
    private String shipName;
    private String shipIdent;
    private float fuelLevel;
    private float fuelCapacity;
    private boolean startLanded = false;
    private String gameMode;
    private long credits;
    private long loan;
    private String group = "";

    public LoadGameEvent(){}

    @Override
    public void updateContext(GameSession gameSession) {
        gameSession.setCurrentLoadGame(this);
        gameSession.getFuelStatus().setFuelLevel(fuelLevel);
        gameSession.getFuelStatus().setFuelCapacity(fuelCapacity);
        this.setNeedUpdateGUI(true);
    }

    @Override
    public void updateGUI(ChampignonScreenJFrame champScr){
        champScr.setgShipNameLabel(shipName);
        champScr.setgMoneyLabel(credits);
        champScr.setcGameModeLabel(gameMode + " "+ group);
    }

    @JsonProperty("FID")
    public String getFid() {
        return fid;
    }

    @JsonProperty("FID")
    public void setFid(String fid) {
        this.fid = fid;
    }

    @JsonProperty("Commander")
    public String getCommanderName() {
        return commanderName;
    }

    @JsonProperty("Commander")
    public void setCommanderName(String commanderName) {
        this.commanderName = commanderName;
    }

    @JsonProperty("Horizons")
    public boolean isHorizonExpansionActive() {
        return isHorizonExpansionActive;
    }

    @JsonProperty("Horizons")
    public void setHorizonExpansionActive(boolean horizonExpansionActive) {
        isHorizonExpansionActive = horizonExpansionActive;
    }

    @JsonProperty("Ship")
    public String getShip() {
        return ship;
    }

    @JsonProperty("Ship")
    public void setShip(String ship) {
        this.ship = ship;
    }

    @JsonProperty("Ship_Localised")
    public String getShipLocalised() {
        return shipLocalised;
    }

    @JsonProperty("Ship_Localised")
    public void setShipLocalised(String shipLocalised) {
        this.shipLocalised = shipLocalised;
    }

    @JsonProperty("ShipID")
    public int getShipID() {
        return shipID;
    }

    @JsonProperty("ShipID")
    public void setShipID(int shipID) {
        this.shipID = shipID;
    }

    @JsonProperty("ShipName")
    public String getShipName() {
        return shipName;
    }

    @JsonProperty("ShipName")
    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    @JsonProperty("ShipIdent")
    public String getShipIdent() {
        return shipIdent;
    }

    @JsonProperty("ShipIdent")
    public void setShipIdent(String shipIdent) {
        this.shipIdent = shipIdent;
    }

    @JsonProperty("FuelLevel")
    public float getFuelLevel() {
        return fuelLevel;
    }

    @JsonProperty("FuelLevel")
    public void setFuelLevel(float fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    @JsonProperty("FuelCapacity")
    public float getFuelCapacity() {
        return fuelCapacity;
    }

    @JsonProperty("FuelCapacity")
    public void setFuelCapacity(float fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    @JsonProperty("StartLanded")
    public boolean isStartLanded() {
        return startLanded;
    }

    @JsonProperty("StartLanded")
    public void setStartLanded(boolean startLanded) {
        this.startLanded = startLanded;
    }

    @JsonProperty("GameMode")
    public String getGameMode() {
        return gameMode;
    }

    @JsonProperty("GameMode")
    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }
    @JsonProperty("Credits")
    public long getCredits() {
        return credits;
    }

    @JsonProperty("Credits")
    public void setCredits(long credits) {
        this.credits = credits;
    }

    @JsonProperty("Loan")
    public long getLoan() {
        return loan;
    }

    @JsonProperty("Loan")
    public void setLoan(long loan) {
        this.loan = loan;
    }

    @JsonProperty("Group")
    public String getGroup() {
        return group;
    }

    @JsonProperty("Group")
    public void setGroup(String group) {
        this.group = group;
    }
}