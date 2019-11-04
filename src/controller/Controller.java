package controller;

import dataclasses.GameSession;
import dataclasses.Star;
import dataclasses.StarSystem;
import dataclasses.events.Event;
import dataclasses.events.EventFactory;
import dataclasses.events.types.LoadGameEvent;
import dataclasses.events.types.LoadoutEvent;
import gui.ChampignonScreenJFrame;
import gui.LogScreenJFrame;

import java.io.*;
import java.util.Vector;

import static tools.Constants.*;
import static tools.JsonParser.*;

public class Controller {

    //Main logic & File reading vars
    private static final String LOG_FOLDER_PATH = System.getProperty("user.home")+"\\Saved Games\\Frontier Developments\\Elite Dangerous\\";
    private String exitMsg = "no message";
    private boolean _exitProgram = false;
    private boolean _loadLatestLog = false;
    private long lastReadFileLength = 0;
    private File journalFile = null;

    //General Status vars
    private StarSystem _starSystem = null;
    private boolean haveFuelScoop = false;
    private boolean _nextStarIsScoopable = false;
    private String _nextStarClass = "?";
    private Vector<String> starClassVec = new Vector<>();
    private float currentFuelLevel = 30.0f;
    private float currentFuelCapacity = 40.0f;
    private float currentJumpMaxFuelCost = 10.0f;

    private ChampignonScreenJFrame _champignonScr;
    private LogScreenJFrame _logScr;
    private GameSession _gameSession;

    public Controller(){
        _champignonScr = new ChampignonScreenJFrame(this);
        _logScr = new LogScreenJFrame();
        _gameSession = new GameSession(_champignonScr);
    }

    public boolean isLogFileUpdated(){
        return (lastReadFileLength < journalFile.length());
    }

    public boolean isLoadLatestLog(){
        return _loadLatestLog;
    }

    public void setLoadLatestLog(boolean bool){
         _loadLatestLog = bool;
    }

    public boolean isExitProgram(){
        return _exitProgram;
    }

    public void setExitProgram(){
        _exitProgram = true;
    }

    //Manipulate File
    public void getLastJournal(){
        boolean errorLoading = false;
        File folder = new File(LOG_FOLDER_PATH);//create a file path to the logs folder
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
                journalFile = new File(LOG_FOLDER_PATH +lastestJournal); //Creating a File for the Journal.log
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
    private Vector<String> readFile(File file, Long fileLength) throws IOException {
        String line;
        Vector<String> content = new Vector<>();
        BufferedReader in = new BufferedReader(new java.io.FileReader(file));
        //noinspection ResultOfMethodCallIgnored
        in.skip(fileLength);
        while((line = in.readLine()) != null){
            if(line.length() > 5){
                content.add(line);
            }
        }
        in.close();
        return content;
    }
    public void readJournal() throws IOException {
        long fileLengthAtTheTime = journalFile.length();//keep the file size at hand right now, in case new messages are being writting inbetween
        Vector<String> newJournalLines = readFile(journalFile, lastReadFileLength);//read file and get a vector of its new lines
        for (String line : newJournalLines) {  //for each of the new lines
            Event event = EventFactory.getEvent(line);
            handleEvent(event);//parse the line to check for events and handle those events case by case
        }
        lastReadFileLength = fileLengthAtTheTime;//set new file size
    }
    private void exitPrompt(){
        for (int i = 10; i > 0; i--){
            String exitMsgTime = " Closing in "+i+"s";
            _champignonScr.setgShipModelLabel(exitMsg+exitMsgTime);
            _logScr.addEvent(exitMsg+exitMsgTime);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("JVM is shit, can't even make a Thread Sleep");
            }
        }
        System.out.println("Closing program.");
        System.exit(0);
    }

    //Big Switch Case
    private void handleEvent(Event event){
        String eventName = event.getEventName();
        try{//TODO remove try catch when all methods are refactored into events.
            switch (eventName){
                case "LoadGame":
                    ((LoadGameEvent) event).updateContext(_gameSession);
                    break;
                case "Loadout":
                    ((LoadoutEvent) event).updateContext(_gameSession);
                    break;
                case "Location":
                    handleLocationEvent(event.getEventString());
                    break;
                case "Scan":
                    handleScanEvent(event.getEventString());
                    break;
                case "FSDTarget":
                    handleFSDTargetEvent(event.getEventString());
                    break;
                case "StartJump": //FSD charging
                    handleStartJumpEvent(event.getEventString());
                    break;
                case "FSDJump": //Jump
                    handleFSDJumpEvent(event.getEventString());
                    break;
                case "FSSSignalDiscovered":
                    handleFSSSignalDiscoveredEvent(event.getEventString());
                    break;
                case "FSSDiscoveryScan":
                    handleFSSDiscoveryScanEvent(event.getEventString());
                    break;
                case "FuelScoop":
                    handleFuelScoopEvent(event.getEventString());
                    break;
                default:
            }
        }catch (NoSuchFieldException e){
            System.out.println(e.getMessage());
        }
    }

    private void handleLocationEvent(String line) throws NoSuchFieldException {
        /*{
        "timestamp":"2019-02-02T15:52:50Z", "event":"Location",
        "Docked":false, "StarSystem":"Swoiwns TD-Z b18-0", "SystemAddress":667195025057, "StarPos":[-279.46875,-422.34375,616.00000],
        "SystemAllegiance":"", "SystemEconomy":"$economy_None;", "SystemEconomy_Localised":"None", "SystemSecondEconomy":"$economy_None;", "SystemSecondEconomy_Localised":"None",
        "SystemGovernment":"$government_None;", "SystemGovernment_Localised":"None",
        "SystemSecurity":"$GAlAXY_MAP_INFO_state_anarchy;", "SystemSecurity_Localised":"Anarchy",
        "Population":0, "Body":"Swoiwns TD-Z b18-0", "BodyID":0, "BodyType":"Star" }
        */
        _champignonScr.setgSystemLabel(getStrContentOf("StarSystem",line));
        _champignonScr.setgSystemPopulationLabel(getIntContentOf("Population",line));
        String security = getStrContentOf("SystemSecurity",line);
        if(security.equals("$GAlAXY_MAP_INFO_state_anarchy;")){
            _champignonScr.setgSystemSecurityLabel("");
        }else{
            _champignonScr.setgSystemSecurityLabel(getStrContentOf("SystemSecurity_Localised",line));
        }
        _champignonScr.setgSystemAllegianceLabel(getStrContentOf("SystemAllegiance",line));
    }
    private void handleScanEvent(String line) throws NoSuchFieldException {
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
                _logScr.addEvent(eventMsg);
            }
        }else if(line.contains("StarType")){
            String starType = getStrContentOf("StarType",line);
            starClassVec.add(starType);

            //System.out.println("Body ID: "+getNumContentOf("BodyID",line)+"Star class: "+getStrContentOf("StarType",line));
            _champignonScr.updateStarClasses(starClassVec);
        }
        //event += state + ", Name: " + getStrContentOf("BodyName", line) + ", Class: " + getStrContentOf("PlanetClass", line);

        if (_starSystem == null){
            System.out.println("_starSystem hasn't been initialized !");
        } else {
            _starSystem.addBody(line);
        }
    }//TODO handle case of handsome body (blackhole, neutron, etc) Rings, in bodies and standalone ring-cluster bodies, only with a detailed surface scanner so its optional
    private void handleFSDTargetEvent(String line){
        //{ "timestamp":"2019-02-02T11:27:00Z", "event":"FSDTarget", "Name":"Swoiwns ZE-X b19-0", "SystemAddress":667194959529 }
    }
    private void handleStartJumpEvent(String line){
        //"event":"StartJump", "JumpType":"Hyperspace", "StarSystem":"Prooe Hypue SF-L d9-264", "SystemAddress":9081582505171, "StarClass":"A"
        if(line.contains("Hyperspace")){
            _starSystem = new StarSystem(line);
            Star mainStar = (Star)_starSystem._systemMap.get(0);
            _nextStarClass = mainStar._starType;
            _nextStarIsScoopable = HYDROGEN_STAR_CLASS.contains(_nextStarClass);
            checkFuelStatus(true);
        }
    }
    private void handleFSDJumpEvent(String line) throws NoSuchFieldException {
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
        _champignonScr.updateStarClasses(starClassVec);

        //Updating Fuel values
        currentFuelLevel = getFloatContentOf("FuelLevel", line);
        _champignonScr.setgFuelLabel(currentFuelLevel,currentFuelCapacity);

        //Updating System properties
        _champignonScr.setgSystemLabel(getStrContentOf("StarSystem",line));
        _champignonScr.setgSystemPopulationLabel(getIntContentOf("Population",line));
        _champignonScr.setgMainStarClassLabel(_nextStarClass);
        String security = getStrContentOf("SystemSecurity",line);
        if(security.equals("$GAlAXY_MAP_INFO_state_anarchy;")){
            _champignonScr.setgSystemSecurityLabel("");
        }else{
            _champignonScr.setgSystemSecurityLabel(getStrContentOf("SystemSecurity_Localised",line));
        }
        _champignonScr.setgSystemAllegianceLabel(getStrContentOf("SystemAllegiance",line));

        //WIP StarSystem HashMap
        if (_starSystem == null){
            _starSystem = new StarSystem(line);
            System.out.println("_starSystem wasn't initialised yet, missing info!");
        }
        _starSystem._starPos = getStarPos(line);
        _starSystem._systemAddress = getLongContentOf("SystemAddress",line);
    }
    private void handleFSSSignalDiscoveredEvent(String line) throws NoSuchFieldException {
        //"SignalName_Localised":"Notable stellar phenomena"
        if (!line.contains("TimeRemaining")){
            _logScr.addEvent("Signal Localized: "+getStrContentOf("SignalName_Localised", line));
        }
        System.out.println("SignalName_Localised: "+getStrContentOf("SignalName_Localised", line));
    }//TODO make a lookup table of usual events, is TimeRemaining a good indicator of a relevant signal?
    private void handleFSSDiscoveryScanEvent(String line) throws NoSuchFieldException {
        //"event":"FSSDiscoveryScan", "Progress":1.000000, "BodyCount":15, "NonBodyCount":0
        if (_starSystem == null){
            System.out.println("_starSystem hasn't been initialized !");
        } else {
            _starSystem._progress = getFloatContentOf("Progress", line);
            _starSystem._bodyCount = getIntContentOf("BodyCount", line);
            _starSystem._nonBodyCount = getIntContentOf("NonBodyCount", line);
        }
    }
    private void handleFuelScoopEvent(String line) throws NoSuchFieldException {
        currentFuelLevel = getFloatContentOf("Total",line);
        _champignonScr.setgFuelLabel(currentFuelLevel,currentFuelCapacity);
        _logScr.addEvent("Refueling: Fuel at "+(int)(100*currentFuelLevel/currentFuelCapacity)+"%");
    }

    //Core logic methods
    public void checkFuelStatus(boolean jumping){
        // update Fuel display
        String eventMsg = "";
        if(currentFuelLevel < currentJumpMaxFuelCost){
            if(jumping) {
                eventMsg += "JUMP FUEL ALERT! FUEL LEVEL CRITICAL! ";
                if(!_gameSession.haveFuelScoop()){
                    eventMsg += "NO FUEL SCOOP ON-BOARD! ";
                }
                if (!_nextStarIsScoopable){
                    eventMsg += "NEXT STAR NOT MAIN-SEQUENCE! ";
                }
            }//end if jumping
            _logScr.addEvent(eventMsg);
        }else if(currentFuelLevel < 2*currentJumpMaxFuelCost){
            if(jumping) {
                eventMsg += "Jump Fuel Warning: Fuel level low! ";
                if(!_gameSession.haveFuelScoop()){
                    eventMsg += "No fuel scoop on board! ";
                }
                if (!_nextStarIsScoopable){
                    eventMsg += "Next star is not a main-sequence star! ";
                }
            }//end if jumping
            _logScr.addEvent(eventMsg);
        }
    }//TODO play a sound if level critical



    public void close() {
        _champignonScr.dispose();
        _logScr.dispose();
    }

    public void runScheduledUpdates() {
        if(_gameSession.is_updateGuiScheduled()){
            _gameSession.updateGUI();
        }
    }
}
