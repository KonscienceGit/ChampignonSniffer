package dataclasses;

import gui.ChampignonScreenJFrame;
import tools.Constants;

import static tools.JsonParser.*;

public class GameSession {
    private boolean _updateGuiScheduled = false;
    private ChampignonScreenJFrame _champScr;
    private String _shipName = "";
    private String _shipModel = "";
    private int _shipID = 0;
    private String _shipIdentityTag = "";
    private float _currentFuelLevel = 30.0f;
    private float _fuelCapacity = 40.0f;
    private float _fuelCapacityMain = 40.0f;
    private float _fuelCapacityReserve = 0.0f;
    private float _JumpMaxFuelCost = 10.0f;
    private boolean _haveFuelScoop = true;
    private String _gameMode = "";
    private long _hullValue = 1;
    private long _modulesValue = 1;
    private long _shipTotalValue = 2;
    private long _rebuyCost = 1;
    private float _hullHealth = 1.0f;
    private float _unladenMass = 1;
    private int _cargoCapacity = 0;
    private float _maxJumpRange = 1;
    private String _fsdModel = "";
    private String _commanderName = "";
    private boolean _isHorizonExpansionActive = true;
    private String _gameModeGroup = "";
    private long _credits = 0;
    private long _loan = 0;

    public GameSession(ChampignonScreenJFrame champScr){
        _champScr = champScr;
    }

    public void updateGUI(){
        _champScr.setgShipModelLabel(_shipModel);
        _champScr.setgShipNameLabel(_shipName);
        _champScr.setgMoneyLabel(_credits);
        _champScr.setgFuelLabel(_currentFuelLevel,_fuelCapacity);
        _champScr.setcGameModeLabel(_gameMode + " "+ _gameModeGroup);
    }


    //Getters
    public boolean is_updateGuiScheduled(){
        return _updateGuiScheduled;
    }

    public String getShipName() {
        return _shipName;
    }

    public String getShipModel() {
        return _shipModel;
    }

    public float getCurrentFuelLevel() {
        return _currentFuelLevel;
    }

    public float getFuelCapacity() {
        return _fuelCapacity;
    }

    public float getJumpMaxFuelCost() {
        return _JumpMaxFuelCost;
    }

    public boolean isHaveFuelScoop() {
        return _haveFuelScoop;
    }

    public String getGameMode() {
        return _gameMode;
    }

    public long getCredits() {
        return _credits;
    }

    //Setters
    public void setShipName(String shipName) {
        this._shipName = shipName;
    }

    public void setShipModel(String shipModel) {
        _shipModel = shipModel;
    }

    public void setCurrentFuelLevel(float currentFuelLevel) {
        _currentFuelLevel = currentFuelLevel;
    }

    public void setFuelCapacity(float fuelCapacity) {
        _fuelCapacity = fuelCapacity;
    }

    public void setFuelCapacity(float fuelCapacityMain, float fuelCapacityReserve) {
        _fuelCapacityMain = fuelCapacityMain;
        _fuelCapacityReserve = fuelCapacityReserve;
        setFuelCapacity(_fuelCapacityMain + _fuelCapacityReserve);
    }

    public void setHaveFuelScoop(boolean haveFuelScoop) {
        _haveFuelScoop = haveFuelScoop;
    }

    public void setGameMode(String gameMode) {
        _gameMode = gameMode;
    }

    public void setCredits(long credits) {
        _credits = credits;
    }

    public void setShipID(int shipID) {
        _shipID = shipID;
    }

    public void setShipIdentityTag(String shipIdent) {
        _shipIdentityTag = shipIdent;
    }

    public void setShipValue(long hullValue, long modulesValue) {
        _hullValue = hullValue;
        _modulesValue = modulesValue;
        _shipTotalValue = _hullValue+ _modulesValue;
    }

    public void setRebuyCost(long rebuyCost) {
        _rebuyCost = rebuyCost;
    }

    public void setHullHealth(float hullHealth) {
        _hullHealth = hullHealth;
    }

    public void setUnladenMass(float unladenMass) {
        _unladenMass = unladenMass;
    }

    public void setCargoCapacity(int cargoCapacity) {
        _cargoCapacity = cargoCapacity;
    }

    public void setMaxJumpRange(float maxJumpRange) {
        _maxJumpRange = maxJumpRange;
    }

    public void setFsdModel(String fsdModel) {
        _fsdModel = fsdModel;
        _JumpMaxFuelCost = Constants.FSD_FUEL_MAP.get(_fsdModel);
    }

    public void setCommanderName(String commanderName) {
        _commanderName = commanderName;
    }

    public void setHorizonExpansionActivated(boolean isHorizonExpansionActive) {
        _isHorizonExpansionActive = isHorizonExpansionActive;
    }

    public void setGameModeGroup(String group) {
        _gameModeGroup = group;
    }

    public void setLoan(long loan) {
        _loan = loan;
    }

    public void scheduleGuiUpdate(){
        _updateGuiScheduled = true;
    }
}