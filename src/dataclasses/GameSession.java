package dataclasses;

import dataclasses.astronomy.StarSystem;
import dataclasses.events.types.*;
import dataclasses.events.types.navigation.FsdJumpEvent;
import dataclasses.events.types.navigation.LocationEvent;
import gui.ChampignonScreenJFrame;
import gui.LogScreenJFrame;

public class GameSession {
    private FuelStatus fuelStatus;
    private LoadGameEvent currentLoadGame;
    private LoadoutEvent currentLoadout;
    private LocationEvent currentLocation;
    private FileheaderEvent currentFileHeader;
    private StarSystem currentStarSystem;
    private StarSystem nextStarSystem;
    private ChampignonScreenJFrame champScr;

    public GameSession(ChampignonScreenJFrame champScr, LogScreenJFrame logScreen){
        this.fuelStatus = new FuelStatus(champScr, logScreen);
        this.champScr = champScr;
    }

    public void updateGUI(){
        getCurrentStarSystem().updateGUI(champScr);
        fuelStatus.updateGUI();
    }


    public void jump(FsdJumpEvent jumpEvent){
        currentStarSystem = getNextStarSystem(jumpEvent);
        nextStarSystem = null;
    }

    public StarSystem getCurrentStarSystem() {
        if (currentStarSystem == null){
            currentStarSystem = new StarSystem();
        }
        return currentStarSystem;
    }

    public StarSystem getNextStarSystem() {
        if (nextStarSystem == null){
            nextStarSystem = new StarSystem();
        }
        return nextStarSystem;
    }

    private StarSystem getNextStarSystem(FsdJumpEvent jumpEvent) {
        if (nextStarSystem == null){
            nextStarSystem = new StarSystem(jumpEvent);
        } else {
            nextStarSystem.initializeWithJmp(jumpEvent);
        }
        return nextStarSystem;
    }

    public FileheaderEvent getCurrentFileHeader() {
        return currentFileHeader;
    }

    public void setCurrentFileHeader(FileheaderEvent currentFileHeader) {
        this.currentFileHeader = currentFileHeader;
    }

    public LoadoutEvent getCurrentLoadout() {
        return currentLoadout;
    }

    public void setCurrentLoadout(LoadoutEvent currentLoadout) {
        this.currentLoadout = currentLoadout;
    }

    public LocationEvent getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(LocationEvent currentLocation) {
        this.currentLocation = currentLocation;
    }

    public LoadGameEvent getCurrentLoadGame() {
        return currentLoadGame;
    }

    public void setCurrentLoadGame(LoadGameEvent currentLoadGame) {
        this.currentLoadGame = currentLoadGame;
    }

    public FuelStatus getFuelStatus() {
        return fuelStatus;
    }
}