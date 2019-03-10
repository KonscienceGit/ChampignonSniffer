package dataclasses;

import java.util.Map;
import java.util.Vector;

public class StarSystem {
    private Map<Integer, AstronomicalBody> systemMap;
    private String systemName = "";
    private int systemAddress = 0;
    private float progress = 0.0f; //if 0.0, then its unknown
    private int bodyCount = 0; //if 0, then it's unknown
    private int nonBodyCount = 0;

    //Created at FSDStartJump (aka wat the 4s contdown beofre jumping)
    public StarSystem(String name, int address, String starClass){
        systemName = name;
        systemAddress = address;

        System.out.println("progress: "+progress+" bodyCount: "+bodyCount);
    }

}
