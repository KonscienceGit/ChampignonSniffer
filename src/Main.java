import dataclasses.Star;
import dataclasses.StarSystem;
import gui.BasicLogScreen;
import gui.ChampignonScreen;
import resources.Resources;
import static resources.CommonMethods.*;

import java.io.*;
import java.util.Vector;

import static resources.Resources.fsdFuelMap;
import static resources.Resources.knownStars;

public class Main {
    //Main logic & File reading vars
    private static final String folderPath = System.getProperty("user.home")+"\\Saved Games\\Frontier Developments\\Elite Dangerous\\";
    private static String exitMsg = "No peculiar reason!";
    private static boolean iStillNeedYou = true;
    private static BasicLogScreen basicLogScreen;
    private static ChampignonScreen champignonScreen;
    private static long lastReadFileLength = 0;
    private static long fileLengthAtTheTime = 0;
    private static File journalFile = null;

    //General Status vars
    private static StarSystem _starSystem = null;
    private static boolean haveFuelScoop = false;
    private static boolean _nextStarIsScoopable = false;
    private static String _nextStarClass = "?";
    private static Vector<String> starClassVec = new Vector<>();
    private static float currentFuelLevel = 30.0f;
    private static float currentFuelCapacity = 40.0f;
    private static float currentJumpMaxFuelCost = 10.0f;

    /////////////////////////////////
    //  MAIN
    /////////////////////////////////
    public static void main(String[] args) throws InterruptedException, IOException {
        //Load the GUI interfaces
        champignonScreen = new ChampignonScreen();
        basicLogScreen = new BasicLogScreen();

        //First read the whole journal to update situation
        getLastJournal();
        readJournal();
        checkFuelStatus(false);

        while(iStillNeedYou){
            if (lastReadFileLength < journalFile.length()){
                readJournal();
            }else{
                Thread.sleep(500);
            }
            if(champignonScreen.isNeedToReload()){
                champignonScreen.setNeedToReload(false);
                getLastJournal();
                readJournal();
                checkFuelStatus(false);
            }
        }
        exitPrompt();
    }


    /////////////////////////////////
    //  METHODS
    /////////////////////////////////

    //Manipulate File
    private static void getLastJournal(){
        boolean errorLoading = false;
        File folder = new File(folderPath);//create a file path to the logs folder
        File[] listOfFiles = folder.listFiles();//list'em, files (and directories)
        //if files are found, look them up, if they are Journal.000000000000.00.log -type, seek the newest one
        if (listOfFiles != null) {
            String fileLargestTimeStamp = "000000000000.00"; //init fileStamp to the lowest value possible
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    if (fileName.startsWith("Journal.") && fileName.endsWith(".log")) {
                        String fileTimeStamp = fileName.substring(8, 23);
                        if (fileTimeStamp.compareTo(fileLargestTimeStamp) > 0) {
                            fileLargestTimeStamp = fileTimeStamp;
                        }
                    }
                }
            }
            if (fileLargestTimeStamp.equals("000000000000.00")){
                exitMsg = "Elite was never launched.";
                errorLoading = true;
            }else{
                String lastestJournal = ("Journal."+fileLargestTimeStamp+".log");
                System.out.println("The latest journal is " +lastestJournal);
                journalFile = new File(folderPath+lastestJournal); //Creating a File for the Journal.log
                lastReadFileLength = 0;
            }
        } else{
            exitMsg = "The game path is incorrect.";
            errorLoading = true;
        }//end if listOfFiles != null
        if (errorLoading){
            exitPrompt();
        }
    }
    private static Vector<String> readFile(File file, Long fileLength) throws IOException {
        String line;
        Vector<String> content = new Vector<>();
        BufferedReader in = new BufferedReader(new java.io.FileReader(file));
        long charSkipped = in.skip(fileLength);
        while((line = in.readLine()) != null){
            if(line.length() > 5){
                content.add(line);
            }
        }
        in.close();
        return content;
    }
    private static void readJournal() throws IOException {
        fileLengthAtTheTime = journalFile.length();//keep the file size at hand right now, in case new messages are being writting inbetween
        Vector<String> newJournalLines = readFile(journalFile, lastReadFileLength);//read file and get a vector of its new lines
        for (String line : newJournalLines) {  //for each of the new lines
            parseLineAndHandleEvents(line);//parse the line to check for events and handle those events case by case
        }
        lastReadFileLength = fileLengthAtTheTime;//set new file size
    }
    private static void exitPrompt(){
        for (int i = 10; i > 0; i--){
            String exitMsgTime = " Closing in "+i+"s";
            champignonScreen.setgShipModelLabel(exitMsg+exitMsgTime);
            basicLogScreen.addEvent(exitMsg+exitMsgTime);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("JVM is shit, can't even make a Thread Sleep");
            }
        }
        System.out.println("Closidfhng program.");
        System.exit(0);
    }

        //Big Switch Case
    private static void parseLineAndHandleEvents(String line){
        String eventName = getStrContentOf("event", line);
        switch (eventName){
            case "LoadGame":
                handleLoadGameEvent(line);
                break;
            case "Loadout":
                handleLoadoutEvent(line);
                break;
            case "Location":
                handleLocationEvent(line);
                break;
            case "Scan":
                handleScanEvent(line);
                break;
            case "FSDTarget":
                handleFSDTargetEvent(line);
                break;
            case "StartJump": //FSD charging
                handleStartJumpEvent(line);
                break;
            case "FSDJump": //Jump
                handleFSDJumpEvent(line);
                break;
            case "FSSSignalDiscovered":
                handleFSSSignalDiscoveredEvent(line);
                break;
            case "FSSDiscoveryScan":
                handleFSSDiscoveryScanEvent(line);
                break;
            case "FuelScoop":
                handleFuelScoopEvent(line);
                break;
            default:
                //System.out.println("Unknown event: ");
                //System.out.println(line);
        }//end switch case
        //System.out.println(line);
    }

    //Switch case Events
    private static void handleLoadGameEvent(String line){
        String shipName = getStrContentOf("Ship",line).replace("_"," ");
        champignonScreen.setgShipModelLabel(shipName);
        champignonScreen.setgShipNameLabel(getStrContentOf("ShipName",line));
        champignonScreen.setgMoneyLabel(getIntContentOf("Credits",line));
        currentFuelLevel = getFloatContentOf("FuelLevel",line);
        currentFuelCapacity = getFloatContentOf("FuelCapacity",line);
        champignonScreen.setgFuelLabel(currentFuelLevel,currentFuelCapacity);
        champignonScreen.setcGameModeLabel(getStrContentOf("GameMode",line));
    }
    private static void handleLoadoutEvent(String line){
        /*"Ship":"Krait_MkII", "ShipID":10, "ShipName":"KRT - Krieger", "ShipIdent":"KRT-KG",
        "HullValue":44160710, "ModulesValue":49082766,
        "HullHealth":0.049595, "Rebuy":4662175,
        "Modules":[ {
        "Slot":"Armour", "Item":"Krait_MkII_Armour_Grade1", "On":true, "Priority":1, "Health":1.000000 }, {
        "Slot":"PowerPlant", "Item":"Int_Powerplant_Size7_Class5", "On":true, "Priority":1, "Health":0.970647, "Value":38913291, "Engineering":{ "Engineer":"Felicity Farseer", "EngineerID":300100, "BlueprintID":128673770, "BlueprintName":"PowerPlant_Stealth", "Level":1, "Quality":0.976000, "ExperimentalEffect":"special_powerplant_cooled", "ExperimentalEffect_Localised":"Thermal Spread", "Modifiers":[ { "Label":"Mass", "Value":41.599998, "OriginalValue":40.000000, "LessIsGood":1 }, { "Label":"PowerCapacity", "Value":29.100000, "OriginalValue":30.000000, "LessIsGood":0 }, { "Label":"HeatEfficiency", "Value":0.270864, "OriginalValue":0.400000, "LessIsGood":1 } ] } }, {
        "Slot":"MainEngines", "Item":"Int_Engine_Size6_Class1", "On":true, "Priority":0, "Health":1.000000, "Value":199747 }, {
        "Slot":"FrameShiftDrive", "Item":"Int_Hyperdrive_Size5_Class5", "On":true, "Priority":0, "Health":0.993149, "Value":5103953, "Engineering":{ "Engineer":"Elvira Martuuk", "EngineerID":300160, "BlueprintID":128673694, "BlueprintName":"FSD_LongRange", "Level":5, "Quality":1.000000, "ExperimentalEffect":"special_fsd_heavy", "ExperimentalEffect_Localised":"Mass Manager", "Modifiers":[ { "Label":"Mass", "Value":26.000000, "OriginalValue":20.000000, "LessIsGood":1 }, { "Label":"Integrity", "Value":93.840004, "OriginalValue":120.000000, "LessIsGood":0 }, { "Label":"PowerDraw", "Value":0.690000, "OriginalValue":0.600000, "LessIsGood":1 }, { "Label":"FSDOptimalMass", "Value":1692.599976, "OriginalValue":1050.000000, "LessIsGood":0 } ] } }, {
        "Slot":"LifeSupport", "Item":"Int_LifeSupport_Size4_Class1", "On":true, "Priority":1, "Health":1.000000, "Value":11349 }, {
        "Slot":"PowerDistributor", "Item":"Int_PowerDistributor_Size1_Class2", "On":false, "Priority":2, "Health":0.955074, "Value":1293, "Engineering":{ "Engineer":"The Dweller", "EngineerID":300180, "BlueprintID":128673740, "BlueprintName":"PowerDistributor_PriorityEngines", "Level":1, "Quality":0.990000, "ExperimentalEffect":"special_powerdistributor_lightweight", "ExperimentalEffect_Localised":"Stripped Down", "Modifiers":[ { "Label":"Mass", "Value":0.450000, "OriginalValue":0.500000, "LessIsGood":1 }, { "Label":"WeaponsCapacity", "Value":10.670000, "OriginalValue":11.000000, "LessIsGood":0 }, { "Label":"WeaponsRecharge", "Value":1.386000, "OriginalValue":1.400000, "LessIsGood":0 }, { "Label":"EnginesCapacity", "Value":10.800000, "OriginalValue":9.000000, "LessIsGood":0 }, { "Label":"EnginesRecharge", "Value":0.579650, "OriginalValue":0.500000, "LessIsGood":0 }, { "Label":"SystemsCapacity", "Value":8.730000, "OriginalValue":9.000000, "LessIsGood":0 }, { "Label":"SystemsRecharge", "Value":0.485000, "OriginalValue":0.500000, "LessIsGood":0 } ] } }, {
        "Slot":"Radar", "Item":"Int_Sensors_Size6_Class5", "On":true, "Priority":1, "Health":1.000000, "Value":3475688, "Engineering":{ "Engineer":"Felicity Farseer", "EngineerID":300100, "BlueprintID":128740671, "BlueprintName":"Sensor_LightWeight", "Level":3, "Quality":1.000000, "Modifiers":[ { "Label":"Mass", "Value":20.000000, "OriginalValue":40.000000, "LessIsGood":1 }, { "Label":"Integrity", "Value":86.799995, "OriginalValue":124.000000, "LessIsGood":0 }, { "Label":"SensorTargetScanAngle", "Value":25.500000, "OriginalValue":30.000000, "LessIsGood":0 } ] } }, {
        "Slot":"FuelTank", "Item":"Int_FuelTank_Size5_Class3", "On":true, "Priority":1, "Health":1.000000, "Value":97754 }, {
        "Slot":"Decal1", "Item":"Decal_NetworkTesters", "On":true, "Priority":1, "Health":1.000000 }, {
        "Slot":"ShipName0", "Item":"Nameplate_Practical01_Grey", "On":true, "Priority":1, "Health":1.000000 }, {
        "Slot":"ShipName1", "Item":"Nameplate_Practical01_Grey", "On":true, "Priority":1, "Health":1.000000 }, {
        "Slot":"Slot01_Size6", "Item":"Int_DetailedSurfaceScanner_Tiny", "On":true, "Priority":0, "AmmoInClip":3, "Health":0.957628, "Value":250000, "Engineering":{ "Engineer":"Lei Cheung", "EngineerID":300120, "BlueprintID":128740151, "BlueprintName":"Sensor_Expanded", "Level":5, "Quality":0.957000, "Modifiers":[ { "Label":"PowerDraw", "Value":0.000000, "OriginalValue":0.000000, "LessIsGood":1 }, { "Label":"DSS_PatchRadius", "Value":29.914000, "OriginalValue":20.000000, "LessIsGood":0 } ] } }, {
        "Slot":"Slot02_Size6", "Item":"Int_FuelTank_Size1_Class3", "On":true, "Priority":1, "Health":1.000000, "Value":1000 }, {
        "Slot":"Slot03_Size5", "Item":"Int_FuelTank_Size1_Class3", "On":true, "Priority":1, "Health":1.000000, "Value":1000 }, {
        "Slot":"Slot04_Size5", "Item":"Int_FuelTank_Size1_Class3", "On":true, "Priority":1, "Health":1.000000, "Value":1000 }, {
        "Slot":"Slot05_Size4", "Item":"Int_FuelScoop_Size4_Class4", "On":true, "Priority":2, "Health":1.000000, "Value":715591 }, {
        "Slot":"Slot06_Size3", "Item":"Int_FuelTank_Size1_Class3", "On":true, "Priority":1, "Health":1.000000, "Value":1000 }, {
        "Slot":"Slot07_Size3", "Item":"Int_Repairer_Size3_Class3", "On":false, "Priority":2, "AmmoInClip":2966, "Health":1.000000, "Value":291600 }, {
        "Slot":"Slot08_Size2", "Item":"Int_Repairer_Size2_Class1", "On":false, "Priority":2, "AmmoInClip":1679, "Health":1.000000, "Value":18000 }, {
        "Slot":"PlanetaryApproachSuite", "Item":"Int_PlanetApproachSuite", "On":true, "Priority":1, "Health":1.000000, "Value":500 }, {
        "Slot":"Bobble01", "Item":"Bobble_Station_Coriolis", "On":true, "Priority":1, "Health":1.000000 }, {
        "Slot":"ShipKitWings", "Item":"Krait_MkII_Shipkit1_Wings4", "On":true, "Priority":1, "Health":1.000000 }, {
        "Slot":"ShipKitTail", "Item":"Krait_MkII_Shipkit1_Tail3", "On":true, "Priority":1, "Health":1.000000 }, {
        "Slot":"WeaponColour", "Item":"WeaponCustomisation_Purple", "On":true, "Priority":1, "Health":1.000000 }, {
        "Slot":"EngineColour", "Item":"EngineCustomisation_Purple", "On":true, "Priority":1, "Health":1.000000 }, {
        "Slot":"VesselVoice", "Item":"VoicePack_Amelie", "On":true, "Priority":1, "Health":1.000000 }, {
        "Slot":"ShipCockpit", "Item":"Krait_MkII_Cockpit", "On":true, "Priority":1, "Health":1.000000 }, {
        "Slot":"CargoHatch", "Item":"ModularCargoBayDoor", "On":false, "Priority":2, "Health":0.950175 } ] }
        */
        //Fuel Scoop equipped?
        haveFuelScoop = line.contains("Int_FuelScoop_Size");
        //FSD fuel capacity
        int position = line.indexOf("FrameShiftDrive");
        String fsdModel = getFromIndexStrContentOf("Item",line, position);
        currentJumpMaxFuelCost = fsdFuelMap.get(fsdModel);
    }
    private static void handleLocationEvent(String line){
        /*{
        "timestamp":"2019-02-02T15:52:50Z", "event":"Location",
        "Docked":false, "StarSystem":"Swoiwns TD-Z b18-0", "SystemAddress":667195025057, "StarPos":[-279.46875,-422.34375,616.00000],
        "SystemAllegiance":"", "SystemEconomy":"$economy_None;", "SystemEconomy_Localised":"None", "SystemSecondEconomy":"$economy_None;", "SystemSecondEconomy_Localised":"None",
        "SystemGovernment":"$government_None;", "SystemGovernment_Localised":"None",
        "SystemSecurity":"$GAlAXY_MAP_INFO_state_anarchy;", "SystemSecurity_Localised":"Anarchy",
        "Population":0, "Body":"Swoiwns TD-Z b18-0", "BodyID":0, "BodyType":"Star" }
        */
        champignonScreen.setgSystemLabel(getStrContentOf("StarSystem",line));
        champignonScreen.setgSystemPopulationLabel(getIntContentOf("Population",line));
        String security = getStrContentOf("SystemSecurity",line);
        if(security.equals("$GAlAXY_MAP_INFO_state_anarchy;")){
            champignonScreen.setgSystemSecurityLabel("");
        }else{
            champignonScreen.setgSystemSecurityLabel(getStrContentOf("SystemSecurity_Localised",line));
        }
        champignonScreen.setgSystemAllegianceLabel(getStrContentOf("SystemAllegiance",line));
    }
    private static void handleScanEvent(String line){
        //"event":"Scan", "ScanType":"AutoScan", "BodyName":"Swoiwns ZU-N b24-1 B A Belt Cluster 2", "BodyID":6, "Parents":[ {"Ring":4}, {"Star":2}, {"Null":0} ], "DistanceFromArrivalLS":62293.093750
        if (line.contains("TerraformState")){
            String terraformable = getStrContentOf("TerraformState", line);
            String eventMsg = "Scan: ";
            boolean sendMsg = false;
            if(!terraformable.equals("")){
                eventMsg += ("Planet is "+terraformable+" ! ");
                sendMsg = true;
            }
            if (line.contains("Rings")){
                eventMsg += "Rings?! TODO ";
                sendMsg = true;
            }
            if (sendMsg){
                basicLogScreen.addEvent(eventMsg);
            }
        }else if(line.contains("StarType")){
            String starType = getStrContentOf("StarType",line);
            starClassVec.add(starType);
            if (!knownStars.contains(starType)){
                writeLog(starType);
            }
            //System.out.println("Body ID: "+getNumContentOf("BodyID",line)+"Star class: "+getStrContentOf("StarType",line));
            champignonScreen.updateStarClasses(starClassVec);
        }
        //event += state + ", Name: " + getStrContentOf("BodyName", line) + ", Class: " + getStrContentOf("PlanetClass", line);

        if (_starSystem == null){
            System.out.println("_starSystem hasn't been initialized !");
        } else {
            _starSystem.addBody(line);
        }
    }//TODO handle case of handsome body (blackhole, neutron, etc) Rings, in bodies and standalone ring-cluster bodies, only with a detailed surface scanner so its optional
    private static void handleFSDTargetEvent(String line){
        //{ "timestamp":"2019-02-02T11:27:00Z", "event":"FSDTarget", "Name":"Swoiwns ZE-X b19-0", "SystemAddress":667194959529 }
    }
    private static void handleStartJumpEvent(String line){
        //"event":"StartJump", "JumpType":"Hyperspace", "StarSystem":"Prooe Hypue SF-L d9-264", "SystemAddress":9081582505171, "StarClass":"A"
        if(line.contains("Hyperspace")){
            _starSystem = new StarSystem(line);
            Star mainStar = (Star)_starSystem._systemMap.get(0);
            _nextStarClass = mainStar._starType;
            _nextStarIsScoopable = Resources.hydrogenStarClass.contains(_nextStarClass);
            checkFuelStatus(true);
        }
    }
    private static void handleFSDJumpEvent(String line){
        /*{
        "timestamp":"2019-02-02T15:53:39Z", "event":"FSDJump",
        "StarSystem":"Swoiwns TI-O c8-2", "SystemAddress":633205363530, "StarPos":[-262.03125,-420.71875,580.37500],
        "SystemAllegiance":"",
        "SystemEconomy":"$economy_None;", "SystemEconomy_Localised":"None", "SystemSecondEconomy":"$economy_None;", "SystemSecondEconomy_Localised":"None",
        "SystemGovernment":"$government_None;", "SystemGovernment_Localised":"None",
        "SystemSecurity":"$GAlAXY_MAP_INFO_state_anarchy;", "SystemSecurity_Localised":"Anarchy",
        "Population":0, "JumpDist":39.697, "FuelUsed":4.665044, "FuelLevel":23.476254 }
         */
        //reset star class map
        starClassVec.clear();
        champignonScreen.updateStarClasses(starClassVec);

        //Updating Fuel values
        currentFuelLevel = getFloatContentOf("FuelLevel", line);
        champignonScreen.setgFuelLabel(currentFuelLevel,currentFuelCapacity);

        //Updating System properties
        champignonScreen.setgSystemLabel(getStrContentOf("StarSystem",line));
        champignonScreen.setgSystemPopulationLabel(getIntContentOf("Population",line));
        champignonScreen.setgMainStarClassLabel(_nextStarClass);
        String security = getStrContentOf("SystemSecurity",line);
        if(security.equals("$GAlAXY_MAP_INFO_state_anarchy;")){
            champignonScreen.setgSystemSecurityLabel("");
        }else{
            champignonScreen.setgSystemSecurityLabel(getStrContentOf("SystemSecurity_Localised",line));
        }
        champignonScreen.setgSystemAllegianceLabel(getStrContentOf("SystemAllegiance",line));

        //WIP StarSystem HashMap
        if (_starSystem == null){
            _starSystem = new StarSystem(line);
            System.out.println("_starSystem wasn't initialised yet, missing info!");
        }
        _starSystem._starPos = getStarPos(line);
        _starSystem._systemAddress = getLongContentOf("SystemAddress",line);
    }
    private static void handleFSSSignalDiscoveredEvent(String line){
        //"SignalName_Localised":"Notable stellar phenomena"
        if (!line.contains("TimeRemaining")){
            basicLogScreen.addEvent("Signal Localized: "+getStrContentOf("SignalName_Localised", line));
        }
        System.out.println("SignalName_Localised: "+getStrContentOf("SignalName_Localised", line));
    }//TODO make a lookup table of usual events, is TimeRemaining a good indicator of a relevant signal?
    private static void handleFSSDiscoveryScanEvent(String line){
        //"event":"FSSDiscoveryScan", "Progress":1.000000, "BodyCount":15, "NonBodyCount":0
        if (_starSystem == null){
            System.out.println("_starSystem hasn't been initialized !");
        } else {
            _starSystem._progress = getFloatContentOf("Progress", line);
            _starSystem._bodyCount = getIntContentOf("BodyCount", line);
            _starSystem._nonBodyCount = getIntContentOf("NonBodyCount", line);
        }
    }
    private static void handleFuelScoopEvent(String line){
        currentFuelLevel = getFloatContentOf("Total",line);
        champignonScreen.setgFuelLabel(currentFuelLevel,currentFuelCapacity);
        basicLogScreen.addEvent("Refueling: Fuel at "+(int)(100*currentFuelLevel/currentFuelCapacity)+"%");
    }

    //Core logic methods
    private static void checkFuelStatus(boolean jumping){
        // update Fuel display
        String eventMsg = "";
        if(currentFuelLevel < currentJumpMaxFuelCost){
            if(jumping) {
                eventMsg += "JUMP FUEL ALERT! FUEL LEVEL CRITICAL! ";
                if(!haveFuelScoop){
                    eventMsg += "NO FUEL SCOOP ONBOARD! ";
                }
                if (!_nextStarIsScoopable){
                    eventMsg += "NEXT STAR NOT MAIN-SEQUENCE! ";
                }
            }//end if jumping
            basicLogScreen.addEvent(eventMsg);
        }else if(currentFuelLevel < 2*currentJumpMaxFuelCost){
            if(jumping) {
                eventMsg += "Jump Fuel Warning: Fuel level low! ";
                if(!haveFuelScoop){
                    eventMsg += "No fuel scoop on board! ";
                }
                if (!_nextStarIsScoopable){
                    eventMsg += "Next star is not a main-sequece star! ";
                }
            }//end if jumping
            basicLogScreen.addEvent(eventMsg);
        }
    }//TODO play a sound if level critical

    //write to log
    private static void writeLog(String text){
        try {
            //Double-check that writing is done in UTF-8
            File file = new File(folderPath + "log.txt");
            PrintWriter writer = new PrintWriter(new FileWriter(file,true));
            writer.append(text).append("\n");
            writer.close();
        } catch (Exception e) {//if an exception occurs
            System.out.println("Error occured while attempting to write to file: " + e.getMessage());
        }
    }
}//end of class
