package dataclasses.events.types.navigation;

import com.fasterxml.jackson.annotation.JsonProperty;
import dataclasses.GameSession;
import dataclasses.events.Event;
import gui.ChampignonScreenJFrame;

public class LocationEvent extends Event {
    private double latitude;
    private double longitude;
    private boolean docked;
    private String starSystem;
    private long systemAdress;
    private double[] starPos;
    private String systemAllegiance;
    private String systemEconomy;
    private String systemEconomy_Localised;
    private String systemSecondEconomy;
    private String systemSecondEconomy_Localised;
    private String systemGovernment;
    private String systemGovernment_Localised;
    private String systemSecurity;
    private String systemSecurity_Localised;
    private long population;
    private String body;
    private int bodyID;
    private String bodyType;

    @Override
    public void updateContext(GameSession gameSession) {
        gameSession.setCurrentLocation(this);
        this.setNeedUpdateGUI(true);
    }

    @Override
    public void updateGUI(ChampignonScreenJFrame champScr){
        champScr.setgSystemLabel(starSystem);
        champScr.setgSystemPopulationLabel(population);
        champScr.setgSystemSecurityLabel(systemSecurity_Localised);
        champScr.setgSystemAllegianceLabel(systemAllegiance);
    }

    @JsonProperty("Latitude")
    public double getLatitude() {
        return latitude;
    }

    @JsonProperty("Latitude")
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @JsonProperty("Longitude")
    public double getLongitude() {
        return longitude;
    }

    @JsonProperty("Longitude")
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @JsonProperty("Docked")
    public boolean isDocked() {
        return docked;
    }

    @JsonProperty("Docked")
    public void setDocked(boolean docked) {
        this.docked = docked;
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
    public long getSystemAdress() {
        return systemAdress;
    }

    @JsonProperty("SystemAddress")
    public void setSystemAdress(long systemAdress) {
        this.systemAdress = systemAdress;
    }

    @JsonProperty("StarPos")
    public double[] getStarPos() {
        return starPos;
    }

    @JsonProperty("StarPos")
    public void setStarPos(double[] starPos) {
        this.starPos = starPos;
    }

    @JsonProperty("SystemAllegiance")
    public String getSystemAllegiance() {
        return systemAllegiance;
    }

    @JsonProperty("SystemAllegiance")
    public void setSystemAllegiance(String systemAllegiance) {
        this.systemAllegiance = systemAllegiance;
    }

    @JsonProperty("SystemEconomy")
    public String getSystemEconomy() {
        return systemEconomy;
    }

    @JsonProperty("SystemEconomy")
    public void setSystemEconomy(String systemEconomy) {
        this.systemEconomy = systemEconomy;
    }

    @JsonProperty("SystemEconomy_Localised")
    public String getSystemEconomy_Localised() {
        return systemEconomy_Localised;
    }

    @JsonProperty("SystemEconomy_Localised")
    public void setSystemEconomy_Localised(String systemEconomy_Localised) {
        this.systemEconomy_Localised = systemEconomy_Localised;
    }

    @JsonProperty("SystemSecondEconomy")
    public String getSystemSecondEconomy() {
        return systemSecondEconomy;
    }

    @JsonProperty("SystemSecondEconomy")
    public void setSystemSecondEconomy(String systemSecondEconomy) {
        this.systemSecondEconomy = systemSecondEconomy;
    }

    @JsonProperty("SystemSecondEconomy_Localised")
    public String getSystemSecondEconomy_Localised() {
        return systemSecondEconomy_Localised;
    }

    @JsonProperty("SystemSecondEconomy_Localised")
    public void setSystemSecondEconomy_Localised(String systemSecondEconomy_Localised) {
        this.systemSecondEconomy_Localised = systemSecondEconomy_Localised;
    }

    @JsonProperty("SystemGovernment")
    public String getSystemGovernment() {
        return systemGovernment;
    }

    @JsonProperty("SystemGovernment")
    public void setSystemGovernment(String systemGovernment) {
        this.systemGovernment = systemGovernment;
    }

    @JsonProperty("SystemGovernment_Localised")
    public String getSystemGovernment_Localised() {
        return systemGovernment_Localised;
    }

    @JsonProperty("SystemGovernment_Localised")
    public void setSystemGovernment_Localised(String systemGovernment_Localised) {
        this.systemGovernment_Localised = systemGovernment_Localised;
    }

    @JsonProperty("SystemSecurity")
    public String getSystemSecurity() {
        return systemSecurity;
    }

    @JsonProperty("SystemSecurity")
    public void setSystemSecurity(String systemSecurity) {
        this.systemSecurity = systemSecurity;
    }

    @JsonProperty("SystemSecurity_Localised")
    public String getSystemSecurity_Localised() {
        return systemSecurity_Localised;
    }

    @JsonProperty("SystemSecurity_Localised")
    public void setSystemSecurity_Localised(String systemSecurity_Localised) {
        this.systemSecurity_Localised = systemSecurity_Localised;
    }

    @JsonProperty("Population")
    public long getPopulation() {
        return population;
    }

    @JsonProperty("Population")
    public void setPopulation(long population) {
        this.population = population;
    }

    @JsonProperty("Body")
    public String getBody() {
        return body;
    }

    @JsonProperty("Body")
    public void setBody(String body) {
        this.body = body;
    }

    @JsonProperty("BodyID")
    public int getBodyID() {
        return bodyID;
    }

    @JsonProperty("BodyID")
    public void setBodyID(int bodyID) {
        this.bodyID = bodyID;
    }

    @JsonProperty("BodyType")
    public String getBodyType() {
        return bodyType;
    }

    @JsonProperty("BodyType")
    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }
}
