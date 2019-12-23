package dataclasses.events.types.warnings;

import dataclasses.GameSession;
import dataclasses.events.Event;

import java.awt.*;
import java.awt.event.KeyEvent;

public class HeatWarning extends Event {



    //TODO put in separate thread
    @Override
    public void updateContext(GameSession gameSession){
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_H);
            robot.delay(100);
            robot.keyRelease(KeyEvent.VK_H);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
