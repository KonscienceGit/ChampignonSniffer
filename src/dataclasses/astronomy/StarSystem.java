package dataclasses.astronomy;

import com.fasterxml.jackson.core.JsonProcessingException;
import dataclasses.events.types.*;
import dataclasses.events.types.navigation.FsdJumpEvent;
import dataclasses.events.types.navigation.StartJumpEvent;
import gui.ChampignonScreenJFrame;
import tools.JacksonMapper;
import tools.TerminalLogger;
import tools.exceptions.NotABodyException;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class StarSystem {
    private Map<Integer, BodyScanEvent> systemMap = new HashMap<>();
    private String systemName = "";
    private long systemAddress = -1;
    private float progress = -1.f;
    private int bodyCount = -1;
    private int nonBodyCount = -1;
    private double[] starSystemPos = new double[0];
    private String systemAllegiance = "";
    private String systemEconomy = "";
    private String systemEconomy_Localised = "";
    private String systemSecondEconomy = "";
    private String systemSecondEconomy_Localised = "";
    private String systemGovernment = "";
    private String systemGovernment_Localised = "";
    private String systemSecurity = "";
    private String systemSecurity_Localised = "";
    private long population = -1;
    private String mainBodyName = "";
    private int mainBodyID = -1;
    private String mainBodyType = "";
    private String mainStarClass = "";
    private float mainStarTemperature = -1;
    private Color mainStarColor;
    private boolean guiUpdateRequired = false;

    public StarSystem() {}

    public StarSystem(FsdJumpEvent fsdJump){
        super();
        initializeWithJmp(fsdJump);
    }

    public void updateGUI(ChampignonScreenJFrame champScr){
        if(guiUpdateRequired){
            guiUpdateRequired = false;
            champScr.setgSystemLabel(systemName);
            champScr.setgSystemPopulationLabel(population);
            champScr.setgSystemAllegianceLabel(systemAllegiance);
            champScr.setgSystemSecurityLabel(systemSecurity_Localised);
            champScr.setgMainStarClassLabel(mainStarClass, mainStarColor);


            champScr.updateStarClasses(this);
//            if (bodyCount > -1){
                //TODO exploration tab
                //progress
                //nonBodies
//            }

        }
    }

    public void addBody(BodyScanEvent body) {
        try {
            int bodyID = body.getBodyIDWithCheck();
            updateMainStarClass(body);
            boolean logChanges = false;
            if(systemMap.containsKey(bodyID) && logChanges){
                TerminalLogger.logTrace("Body already in map, time: " + body.getTimestamp());
                TerminalLogger.logTrace("Old scan: \n" + JacksonMapper.getMapper().writerWithDefaultPrettyPrinter().writeValueAsString(systemMap.get(bodyID)) );
                TerminalLogger.logTrace("New scan: \n" + JacksonMapper.getMapper().writerWithDefaultPrettyPrinter().writeValueAsString(body) );
            }
            systemMap.put(bodyID, body );
            guiUpdateRequired = true;
        } catch (NotABodyException | JsonProcessingException e) {
            TerminalLogger.logStackTrace(e);
        }
    }

    public void initializeWithFSSD(FssDiscoveryScanEvent fssDScan){
        systemName = fssDScan.getSystemName();
        systemAddress = fssDScan.getSystemAddress();
        progress = fssDScan.getProgress();
        bodyCount = fssDScan.getBodyCount();
        nonBodyCount = fssDScan.getNonBodyCount();
        guiUpdateRequired = true;
    }

    public void initializeWithJmp(FsdJumpEvent fsdJump){
        systemName = fsdJump.getStarSystem();
        systemAddress = fsdJump.getSystemAddress();
        starSystemPos = fsdJump.getStarSystemPos();
        systemAllegiance = fsdJump.getSystemAllegiance();
        systemEconomy = fsdJump.getSystemEconomy();
        systemEconomy_Localised = fsdJump.getSystemEconomy_Localised();
        systemSecondEconomy = fsdJump.getSystemSecondEconomy();
        systemSecondEconomy_Localised = fsdJump.getSystemSecondEconomy_Localised();
        systemGovernment = fsdJump.getSystemGovernment();
        systemGovernment_Localised = fsdJump.getSystemGovernment_Localised();
        systemSecurity = fsdJump.getSystemSecurity();
        systemSecurity_Localised = fsdJump.getSystemSecurity_Localised();
        population = fsdJump.getPopulation();
        mainBodyName = fsdJump.getBody();
        mainBodyID = fsdJump.getBodyID();
        mainBodyType = fsdJump.getBodyType();
        guiUpdateRequired = true;
    }

    public void initializeWithStrJmp(StartJumpEvent startJumpEvent){
        systemName = startJumpEvent.getStarSystem();
        systemAddress = startJumpEvent.getSystemAddress();
        mainBodyType = startJumpEvent.getStarClass();
        guiUpdateRequired = true;
    }

    private void updateMainStarClass(BodyScanEvent body){
        if (mainBodyID == body.getBodyID()){
            mainStarClass = body.getStarType();
            mainStarTemperature = body.getSurfaceTemperature();
            mainStarColor = body.getStarColor();
            guiUpdateRequired = true;
        }
    }

    public Map<Integer, BodyScanEvent> getSystemMap() {
        return systemMap;
    }

    public String getSystemName() {
        return systemName;
    }

    public long getSystemAddress() {
        return systemAddress;
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
        guiUpdateRequired = true;
    }

    public int getBodyCount() {
        return bodyCount;
    }

    public int getNonBodyCount() {
        return nonBodyCount;
    }

    public double[] getStarSystemPos() {
        return starSystemPos;
    }

    public String getSystemAllegiance() {
        return systemAllegiance;
    }

    public String getSystemEconomy() {
        return systemEconomy;
    }

    public String getSystemEconomy_Localised() {
        return systemEconomy_Localised;
    }

    public String getSystemSecondEconomy() {
        return systemSecondEconomy;
    }

    public String getSystemSecondEconomy_Localised() {
        return systemSecondEconomy_Localised;
    }

    public String getSystemGovernment() {
        return systemGovernment;
    }

    public String getSystemGovernment_Localised() {
        return systemGovernment_Localised;
    }

    public String getSystemSecurity() {
        return systemSecurity;
    }

    public String getSystemSecurity_Localised() {
        return systemSecurity_Localised;
    }

    public long getPopulation() {
        return population;
    }

    public String getMainBodyName() {
        return mainBodyName;
    }

    public int getMainBodyID() {
        return mainBodyID;
    }

    public String getMainBodyType() {
        return mainBodyType;
    }
}
