import controller.Controller;

import java.io.*;

public class Main {

    private static Controller _controller;

    public static void main(String[] args) throws InterruptedException, IOException {
        _controller = new Controller();
        initialize();

        while(!_controller.isExitProgram()){
            if (_controller.isLogFileUpdated()){
                _controller.readJournal();
                _controller.runScheduledUpdates();
            }else{
                Thread.sleep(500);
            }
            if(_controller.isLoadLatestLog()){
                _controller.setLoadLatestLog(false);
                initialize();
            }
        }
        _controller.close();
    }

    private static void initialize() throws IOException {
        _controller.getLastJournal();
        _controller.readJournal();
        _controller.checkFuelStatus(false);
        _controller.runScheduledUpdates();
    }
}
