package controller;

import dataclasses.GameSession;
import dataclasses.astronomy.StarSystem;
import dataclasses.events.Event;
import dataclasses.events.EventFactory;
import gui.ChampignonScreenJFrame;
import gui.LogScreenJFrame;
import tools.TerminalLogger;

import java.io.*;
import java.util.Stack;
import java.util.Vector;

import static tools.JsonParser.*;

public class Controller {

    //Main logic & File reading vars
    private static final String LOG_FOLDER_PATH = System.getProperty("user.home")+"\\Saved Games\\Frontier Developments\\Elite Dangerous\\";
    private String exitMsg = "no message";
    private boolean _exitProgram = false;
    private boolean _loadLatestLog = false;
    private long lastReadFileLength = 0;
    private File journalFile = null;
    private Stack<Event> eventGuiUpdateList = new Stack<>();

    //General Status vars
    private StarSystem _starSystem = null;
    private float currentFuelLevel = 30.0f;

    private ChampignonScreenJFrame champignonScr;
    private LogScreenJFrame logScr;
    private GameSession gameSession;

    public Controller(){
        champignonScr = new ChampignonScreenJFrame(this);
        logScr = new LogScreenJFrame();
        gameSession = new GameSession(champignonScr, logScr);
    }

    //Manipulate File
    public boolean isLogFileUpdated(){
        return (lastReadFileLength < journalFile.length());
    }
    public boolean isLoadLatestLog(){
        return _loadLatestLog;
    }
    public void setLoadLatestLog(boolean bool){
        _loadLatestLog = bool;
    }

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

    //Event creation
    public void readJournal() throws IOException {
        long fileLengthAtTheTime = journalFile.length();//keep the file size at hand right now, in case new messages are being writting inbetween
        Vector<String> newJournalLines = readFile(journalFile, lastReadFileLength);//read file and get a vector of its new lines
        for (String line : newJournalLines) {  //for each of the new lines
            Event event = EventFactory.getEvent(line);
            event.updateContext(gameSession);
            if (event.isNeedUpdateGUI()) eventGuiUpdateList.add(event);
            TerminalLogger.logTrace("Event: "+event.getEvent());
        }
        lastReadFileLength = fileLengthAtTheTime;//set new file size
    }

    private void handleFSSSignalDiscoveredEvent(String line) throws NoSuchFieldException {
        //"SignalName_Localised":"Notable stellar phenomena"
        if (!line.contains("TimeRemaining")){
            logScr.addEvent("Signal Localized: "+getStrContentOf("SignalName_Localised", line));
        }
        System.out.println("SignalName_Localised: "+getStrContentOf("SignalName_Localised", line));
    }//TODO make a lookup table of usual events, is TimeRemaining a good indicator of a relevant signal?

    private void handleFuelScoopEvent(String line) throws NoSuchFieldException {
        currentFuelLevel = getFloatContentOf("Total",line);
        gameSession.getFuelStatus().setFuelLevel(currentFuelLevel);
        gameSession.getFuelStatus().logRefuelStatus();
    }

    //Core logic methods
    public boolean isExitProgram(){
        return _exitProgram;
    }
    public void setExitProgram(){
        _exitProgram = true;
    }
    public void checkFuelStatus(boolean jumping){
        gameSession.getFuelStatus().checkFuelStatus(jumping);
    }
    public void close() {
        champignonScr.dispose();
        logScr.dispose();
    }
    public void runScheduledUpdates() {
        gameSession.updateGUI();
        while(!eventGuiUpdateList.isEmpty()){
            eventGuiUpdateList.pop().updateGUI(champignonScr);
        }
    }
    private void exitPrompt(){
        for (int i = 10; i > 0; i--){
            String exitMsgTime = " Closing in "+i+"s";
            champignonScr.setgShipModelLabel(exitMsg+exitMsgTime);
            logScr.addEvent(exitMsg+exitMsgTime);
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
}
