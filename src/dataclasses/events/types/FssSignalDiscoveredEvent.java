package dataclasses.events.types;

import dataclasses.GameSession;
import dataclasses.events.Event;

public class FssSignalDiscoveredEvent extends Event {
    @Override
    public void updateContext(GameSession gameSession) {
//        gameSession.setCurrentLoadout(this);
        this.setNeedUpdateGUI(true);
    }
}
