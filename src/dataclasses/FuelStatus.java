package dataclasses;

import gui.ChampignonScreenJFrame;
import gui.LogScreenJFrame;
import tools.TerminalLogger;

public class FuelStatus {
    private float fuelLevel;
    private float fuelCapacity;
    private final ChampignonScreenJFrame champScr;
    private final LogScreenJFrame logScreen;
    private boolean haveFuelScoop;

    public FuelStatus(ChampignonScreenJFrame champScr, LogScreenJFrame logScreen) {
        this.champScr = champScr;
        this.logScreen = logScreen;
    }

    public void updateGUI(){
        //TODO update fuell level on GUI
    }

    public float getFuelLevel() {
        return fuelLevel;
    }

    public void setFuelLevel(float fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    public float getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(float fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public void setHaveFuelScoop(boolean haveFuelScoop) {
        this.haveFuelScoop = haveFuelScoop;
    }

    public boolean isHaveFuelScoop() {
        return haveFuelScoop;
    }

    public void logRefuelStatus() {
        TerminalLogger.logTrace("Refueling: " + (int)100.*fuelLevel/fuelCapacity + "%");
    }

    public void checkFuelStatus(boolean jumping) {
        //TODO handle chekc for jump, fuel level etc
    }
}
