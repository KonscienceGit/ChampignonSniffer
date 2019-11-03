package dataclasses.events.types;

import dataclasses.GameSession;
import dataclasses.events.Event;

import static tools.JsonParser.*;

public class LoadGameEvent extends Event {
    private final String _commanderName;
    private final boolean _isHorizonExpansionActive;
    private final String _shipModelLocalised;
    private final int _shipID;
    private final String _shipName;
    private final String _shipIdent;
    private final float _currentFuelLevel;
    private final String _gameMode;
    private final String _group;
    private final long _credits;
    private final long _loan;


    public LoadGameEvent(String eventStr, String eventName) throws NoSuchFieldException {
        super(eventStr, eventName);
        _commanderName = getStrContentOf("Commander", eventStr);
        _isHorizonExpansionActive = getBoolContentOf("Horizons", eventStr);
        _shipModelLocalised = getStrContentOf("Ship_Localised",eventStr);
        _shipID = getIntContentOf("ShipID",eventStr);
        _shipName = getStrContentOf("ShipName",eventStr);
        _shipIdent = getStrContentOf("ShipIdent",eventStr);
        _currentFuelLevel = getFloatContentOf("FuelLevel", eventStr);
        _gameMode = getStrContentOf("GameMode", eventStr);
        String group;
        try{
            group = getStrContentOf("Group", eventStr);
        } catch (NoSuchFieldException e){
            group = "";
        }
        _group = group;
        _credits = getLongContentOf("Credits", eventStr);
        _loan = getLongContentOf("Loan", eventStr);
    }

    public void updateContext(GameSession gameSession) {
        gameSession.setCommanderName(_commanderName);
        gameSession.setHorizonExpansionActivated(_isHorizonExpansionActive);
        gameSession.setShipModel(_shipModelLocalised);
        gameSession.setShipID(_shipID);
        gameSession.setShipName(_shipName);
        gameSession.setShipIdentityTag(_shipIdent);
        gameSession.setCurrentFuelLevel(_currentFuelLevel);
        gameSession.setGameMode(_gameMode);
        if (!_group.isEmpty()){
            gameSession.setGameModeGroup(_group);
        }
        gameSession.setCredits(_credits);
        gameSession.setLoan(_loan);

        gameSession.scheduleGuiUpdate();
    }
}


/* as of 03/11/2019
{ "timestamp":"2019-11-03T16:04:36Z", "event":"LoadGame",
"FID":"F2925166",
"Commander":"Konscience",
"Horizons":true,
"Ship":"Krait_MkII",
"Ship_Localised":"Krait Mk II",
"ShipID":10, "ShipName":"KRT - Krieger", "ShipIdent":"KRT-KG",
"FuelLevel":22.051260, "FuelCapacity":32.000000,
"GameMode":"Group",
"Group":"Mobius",
"Credits":247532410,
"Loan":0 }
 */