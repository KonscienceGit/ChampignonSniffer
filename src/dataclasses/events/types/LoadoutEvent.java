package dataclasses.events.types;

import dataclasses.GameSession;
import dataclasses.events.Event;

import static tools.JsonParser.*;

public class LoadoutEvent extends Event {
    private final String _shipModel;
    private final int _shipID;
    private final String _shipName;
    private final String _shipIdent;
    private final long _hullValue;
    private final long _modulesValue;
    private final float _hullHealth;
    private final float _unladenMass;
    private final int _cargoCapacity;
    private final float _maxJumpRange;
    private final float _fuelCapacityMain;
    private final float _fuelCapacityReserve;
    private final long _rebuyCost;
    private final String _fsdModel;
    private final boolean _haveFuelScoop;

    public LoadoutEvent(String eventObj, String eventName) throws NoSuchFieldException {
        super(eventObj, eventName);
        _shipModel = getStrContentOf("Ship",eventObj).replace("_"," ");
        _shipID = getIntContentOf("ShipID",eventObj);
        _shipName = getStrContentOf("ShipName",eventObj);
        _shipIdent = getStrContentOf("ShipIdent",eventObj);

        _hullValue = getLongContentOf("HullValue", eventObj);
        _modulesValue = getLongContentOf("ModulesValue", eventObj);
        _hullHealth = getFloatContentOf("HullHealth", eventObj);
        _unladenMass = getFloatContentOf("UnladenMass", eventObj);
        _cargoCapacity = getIntContentOf("CargoCapacity", eventObj);
        _maxJumpRange = getFloatContentOf("MaxJumpRange", eventObj);
        String fuelCapacityObj = getObjectSubstring("FuelCapacity", eventObj);
        _fuelCapacityMain = getFloatContentOf("Main", fuelCapacityObj);
        _fuelCapacityReserve = getFloatContentOf("Reserve", fuelCapacityObj);
        _rebuyCost = getLongContentOf("Rebuy", eventObj);

        String[] modulesArray = getArray("Modules", eventObj);
        String fsdModuleObject = getLineInArrayWithMentionOf(modulesArray, "FrameShiftDrive" );
        _fsdModel = getStrContentOf("Item", fsdModuleObject);
        String fuelScoopModule = getLineInArrayWithMentionOf(modulesArray, "fuelscoop" );
        _haveFuelScoop = !fuelScoopModule.equals("");
    }

    public void updateContext(GameSession gameSession) {
        gameSession.setShipModel(_shipModel);
        gameSession.setShipID(_shipID);
        gameSession.setShipName(_shipName);
        gameSession.setShipIdentityTag(_shipIdent);
        gameSession.setShipValue(_hullValue, _modulesValue);
        gameSession.setHullHealth(_hullHealth);
        gameSession.setUnladenMass(_unladenMass);
        gameSession.setCargoCapacity(_cargoCapacity);
        gameSession.setMaxJumpRange(_maxJumpRange);
        gameSession.setFuelCapacity(_fuelCapacityMain, _fuelCapacityReserve);
        gameSession.setRebuyCost(_rebuyCost);
        gameSession.setFsdModel(_fsdModel);
        gameSession.setHaveFuelScoop(_haveFuelScoop);

        gameSession.scheduleGuiUpdate();
    }
}

/*{ as of 03/11/2019
"timestamp":"2019-11-03T11:51:39Z", "event":"Loadout",
"Ship":"krait_mkii", "ShipID":10, "ShipName":"KRT - Krieger", "ShipIdent":"KRT-KG",
"HullValue":44160710, "ModulesValue":8772645, "HullHealth":1.000000,
"UnladenMass":433.080017, "CargoCapacity":128,
"MaxJumpRange":45.321304, "FuelCapacity":{ "Main":32.000000, "Reserve":0.630000 },
"Rebuy":2646670,
"Modules":[
    { "Slot":"ShipCockpit", "Item":"krait_mkii_cockpit", "On":true, "Priority":1, "Health":1.000000 },
    { "Slot":"CargoHatch", "Item":"modularcargobaydoor", "On":true, "Priority":3, "Health":1.000000 },
    { "Slot":"TinyHardpoint1", "Item":"hpt_heatsinklauncher_turret_tiny", "On":true, "Priority":0, "AmmoInClip":1, "AmmoInHopper":2, "Health":1.000000 },
    { "Slot":"PaintJob", "Item":"paintjob_krait_mkii_trims_yellowblack", "On":true, "Priority":1, "Health":1.000000 },
    { "Slot":"Armour", "Item":"krait_mkii_armour_grade1", "On":true, "Priority":1, "Health":1.000000, "Engineering":{ "Engineer":"Selene Jean", "EngineerID":300210, "BlueprintID":128673643, "BlueprintName":"Armour_HeavyDuty", "Level":4, "Quality":1.000000, "ExperimentalEffect":"special_armour_chunky", "ExperimentalEffect_Localised":"Deep Plating", "Modifiers":[ { "Label":"DefenceModifierHealthMultiplier", "Value":146.888016, "OriginalValue":79.999992, "LessIsGood":0 }, { "Label":"KineticResistance", "Value":-18.656004, "OriginalValue":-20.000004, "LessIsGood":0 }, { "Label":"ThermicResistance", "Value":1.120007, "OriginalValue":0.000000, "LessIsGood":0 }, { "Label":"ExplosiveResistance", "Value":-38.431992, "OriginalValue":-39.999996, "LessIsGood":0 } ] } },
    { "Slot":"PowerPlant", "Item":"int_powerplant_size7_class2", "On":true, "Priority":1, "Health":1.000000, "Engineering":{ "Engineer":"Felicity Farseer", "EngineerID":300100, "BlueprintID":128673770, "BlueprintName":"PowerPlant_Stealth", "Level":1, "Quality":1.000000, "Modifiers":[ { "Label":"Mass", "Value":33.279999, "OriginalValue":32.000000, "LessIsGood":1 }, { "Label":"PowerCapacity", "Value":21.825001, "OriginalValue":22.500000, "LessIsGood":0 }, { "Label":"HeatEfficiency", "Value":0.562500, "OriginalValue":0.750000, "LessIsGood":1 } ] } },
    { "Slot":"MainEngines", "Item":"int_engine_size6_class1", "On":true, "Priority":0, "Health":1.000000, "Engineering":{ "Engineer":"Elvira Martuuk", "EngineerID":300160, "BlueprintID":128673665, "BlueprintName":"Engine_Tuned", "Level":1, "Quality":1.000000, "Modifiers":[ { "Label":"EngineOptimalMass", "Value":940.800049, "OriginalValue":960.000000, "LessIsGood":0 }, { "Label":"EngineOptPerformance", "Value":108.000015, "OriginalValue":100.000000, "LessIsGood":0 }, { "Label":"EngineHeatRate", "Value":1.040000, "OriginalValue":1.300000, "LessIsGood":1 } ] } },
    { "Slot":"FrameShiftDrive", "Item":"int_hyperdrive_size5_class5", "On":true, "Priority":0, "Health":1.000000, "Engineering":{ "Engineer":"Elvira Martuuk", "EngineerID":300160, "BlueprintID":128673694, "BlueprintName":"FSD_LongRange", "Level":5, "Quality":1.000000, "ExperimentalEffect":"special_fsd_heavy", "ExperimentalEffect_Localised":"Mass Manager", "Modifiers":[ { "Label":"Mass", "Value":26.000000, "OriginalValue":20.000000, "LessIsGood":1 }, { "Label":"Integrity", "Value":93.840004, "OriginalValue":120.000000, "LessIsGood":0 }, { "Label":"PowerDraw", "Value":0.690000, "OriginalValue":0.600000, "LessIsGood":1 }, { "Label":"FSDOptimalMass", "Value":1692.599976, "OriginalValue":1050.000000, "LessIsGood":0 } ] } },
    { "Slot":"LifeSupport", "Item":"int_lifesupport_size4_class2", "On":true, "Priority":1, "Health":1.000000 },
    { "Slot":"PowerDistributor", "Item":"int_powerdistributor_size1_class2", "On":true, "Priority":0, "Health":1.000000 },
    { "Slot":"Radar", "Item":"int_sensors_size6_class2", "On":true, "Priority":1, "Health":1.000000, "Engineering":{ "Engineer":"Felicity Farseer", "EngineerID":300100, "BlueprintID":128740671, "BlueprintName":"Sensor_LightWeight", "Level":3, "Quality":1.000000, "Modifiers":[ { "Label":"Mass", "Value":8.000000, "OriginalValue":16.000000, "LessIsGood":1 }, { "Label":"Integrity", "Value":63.000000, "OriginalValue":90.000000, "LessIsGood":0 }, { "Label":"SensorTargetScanAngle", "Value":25.500000, "OriginalValue":30.000000, "LessIsGood":0 } ] } },
    { "Slot":"FuelTank", "Item":"int_fueltank_size5_class3", "On":true, "Priority":1, "Health":1.000000 },
    { "Slot":"Decal1", "Item":"decal_networktesters", "On":true, "Priority":1, "Health":1.000000 },
    { "Slot":"ShipName0", "Item":"nameplate_practical01_grey", "On":true, "Priority":1, "Health":1.000000 },
    { "Slot":"ShipName1", "Item":"nameplate_practical01_grey", "On":true, "Priority":1, "Health":1.000000 },
    { "Slot":"Slot01_Size6", "Item":"int_cargorack_size6_class1", "On":true, "Priority":1, "Health":1.000000 },
    { "Slot":"Slot02_Size6", "Item":"int_cargorack_size6_class1", "On":true, "Priority":1, "Health":1.000000 },
    { "Slot":"Slot05_Size4", "Item":"int_fuelscoop_size4_class4", "On":true, "Priority":0, "Health":1.000000 },
    { "Slot":"Slot07_Size3", "Item":"int_repairer_size3_class3", "On":false, "Priority":0, "Health":1.000000 },
    { "Slot":"Slot08_Size2", "Item":"int_detailedsurfacescanner_tiny", "On":true, "Priority":0, "Health":1.000000, "Engineering":{ "Engineer":"Lei Cheung", "EngineerID":300120, "BlueprintID":128740151, "BlueprintName":"Sensor_Expanded", "Level":5, "Quality":0.957000, "Modifiers":[ { "Label":"PowerDraw", "Value":0.000000, "OriginalValue":0.000000, "LessIsGood":1 }, { "Label":"DSS_PatchRadius", "Value":29.914000, "OriginalValue":20.000000, "LessIsGood":0 } ] } },
    { "Slot":"PlanetaryApproachSuite", "Item":"int_planetapproachsuite", "On":true, "Priority":1, "Health":1.000000 },
    { "Slot":"Bobble01", "Item":"bobble_station_coriolis", "On":true, "Priority":1, "Health":1.000000 },
    { "Slot":"ShipKitWings", "Item":"krait_mkii_shipkit1_wings4", "On":true, "Priority":1, "Health":1.000000 },
    { "Slot":"ShipKitTail", "Item":"krait_mkii_shipkit1_tail3", "On":true, "Priority":1, "Health":1.000000 },
    { "Slot":"ShipKitBumper", "Item":"krait_mkii_shipkit1_bumper1", "On":true, "Priority":1, "Health":1.000000 },
    { "Slot":"WeaponColour", "Item":"weaponcustomisation_purple", "On":true, "Priority":1, "Health":1.000000 },
    { "Slot":"EngineColour", "Item":"enginecustomisation_purple", "On":true, "Priority":1, "Health":1.000000 },
    { "Slot":"VesselVoice", "Item":"voicepack_maksim", "On":true, "Priority":1, "Health":1.000000 }
] }
 */