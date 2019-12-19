package dataclasses.events.types.navigation;

import com.fasterxml.jackson.annotation.JsonProperty;
import dataclasses.GameSession;
import dataclasses.events.Event;

/**
 * FSD JUMP event occur when a jump in hperspace just finished.
 */
public class FsdJumpEvent extends Event {

    private String starSystem;
    private long systemAddress;
    private double[] starSystemPos;
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
    private float jumpDist;
    private float fuelUsed;
    private float fuelLevel;

    @Override
    public void updateContext(GameSession gameSession) {
        gameSession.jump(this);
        this.setNeedUpdateGUI(true);
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

    @JsonProperty("StarPos")
    public double[] getStarSystemPos() {
        return starSystemPos;
    }

    @JsonProperty("StarPos")
    public void setStarSystemPos(double[] starSystemPos) {
        this.starSystemPos = starSystemPos;
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

    @JsonProperty("JumpDist")
    public float getJumpDist() {
        return jumpDist;
    }

    @JsonProperty("JumpDist")
    public void setJumpDist(float jumpDist) {
        this.jumpDist = jumpDist;
    }

    @JsonProperty("FuelUsed")
    public float getFuelUsed() {
        return fuelUsed;
    }

    @JsonProperty("FuelUsed")
    public void setFuelUsed(float fuelUsed) {
        this.fuelUsed = fuelUsed;
    }

    @JsonProperty("FuelLevel")
    public float getFuelLevel() {
        return fuelLevel;
    }

    @JsonProperty("FuelLevel")
    public void setFuelLevel(float fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    //    //reset star class map
//        starClassVec.clear();
//        champignonScr.updateStarClasses(starClassVec);
//
//    //Updating Fuel values
//    currentFuelLevel = getFloatContentOf("FuelLevel", line);
//        gameSession.getFuelStatus().setFuelLevel(currentFuelLevel);
//
//    //Updating System properties
//        champignonScr.setgSystemLabel(getStrContentOf("StarSystem",line));
//        champignonScr.setgSystemPopulationLabel(getIntContentOf("Population",line));
//        champignonScr.setgMainStarClassLabel(_nextStarClass);
//    String security = getStrContentOf("SystemSecurity",line);
//        if(security.equals("$GAlAXY_MAP_INFO_state_anarchy;")){
//        champignonScr.setgSystemSecurityLabel("");
//    }else{
//        champignonScr.setgSystemSecurityLabel(getStrContentOf("SystemSecurity_Localised",line));
//    }
//        champignonScr.setgSystemAllegianceLabel(getStrContentOf("SystemAllegiance",line));
}
