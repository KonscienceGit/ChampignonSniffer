package dataclasses;

import java.util.HashMap;
import java.util.Map;
import static tools.JsonParser.*;

public class StarSystem {
    public Map<Integer, AstronomicalBody> _systemMap;
    public String _systemName = "";
    public long _systemAddress = 0;
    public float _progress = 0.0f; //if 0.0, then its unknown
    public int _bodyCount = 0; //if 0, then it's unknown
    public int _nonBodyCount = 0;
    public double[] _starPos = new double[3];

    //Created at FSDStartJump (aka at the 4s countdown before jumping)
    public StarSystem(String line){
        _systemName = getStrContentOf("StarSystem",line);
        _systemAddress = getLongContentOf("SystemAddress",line);
        String mainStarClass = getStrContentOf("StarClass",line);
        _systemMap = new HashMap<>();
        _systemMap.put(0, new Star(_systemName, mainStarClass));
        //printCreation();
    }

    public void addBody(String line){
        int position = getIntContentOf("BodyID", line);
        _systemMap.put(position, AstronomicalBody.getClass(line) );
        _systemMap.get(position).print();
    }

    public void printCreation(){
        System.out.println("Created new StarSystem");
        System.out.println("_progress: "+ _progress +" _bodyCount: "+ _bodyCount);
        Star firstStar = (Star)_systemMap.get(0);
        System.out.println("_bodyId: "+ firstStar._bodyId);
        System.out.println("_bodyName: "+ firstStar._bodyName);
        System.out.println("_starType: "+ firstStar._starType);
        System.out.println("_bodyType: "+ firstStar._bodyType);
    }
}
