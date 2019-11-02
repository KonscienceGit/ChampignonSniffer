package dataclasses;

import static tools.JsonParser.*;

public class Star extends AstronomicalBody {
    /*String _detailedScanContent = "";
    String _bodyName = "";
    int _bodyId = 0;*/

    public float _distanceFromArrivalLS = 0.0f;
    public String _starType = "";
    public float _stellarMass = 0.0f;
    public double _radius = 0.0f;
    public float _absoluteMagnitude = 0.0f;
    public long _age_MY = 0;
    public double _surfaceTemperature = 0.0f;
    public String _luminosity = "";
    public double _rotationPeriod = 0.0f;
    public float _axialTilt = 0.0f;

    //First Star discovery by FSDStardJump
    public Star (String name, String starClass){
        _bodyId = 0;
        _bodyName = name;
        _starType = starClass;
        _bodyType = BODY_STAR;
    }

    public Star (String line){
        _detailedScanContent = line;
        _bodyType = BODY_STAR;
        _bodyName = getStrContentOf("BodyName",line);
        _bodyId = getIntContentOf("BodyID",line);
        _distanceFromArrivalLS = getFloatContentOf("DistanceFromArrivalLS",line);
        _starType = getStrContentOf("StarType", line);
        _stellarMass = getFloatContentOf("StellarMass",line);
        _radius = getDoubleContentOf("Radius",line);
        _absoluteMagnitude = getFloatContentOf("AbsoluteMagnitude",line);
        _age_MY = getLongContentOf("Age_MY",line);
        _surfaceTemperature = getDoubleContentOf("SurfaceTemperature",line);
        _luminosity = getStrContentOf("Luminosity",line);
        _rotationPeriod = getDoubleContentOf("RotationPeriod",line);
        _axialTilt = getFloatContentOf("AxialTilt",line);

    }

    public void print(){
        System.out.println();
        System.out.println("/************BODY STAR************/");
        System.out.println("_bodyType : "+_bodyType);
        System.out.println("_bodyName : "+_bodyName);
        System.out.println("_bodyId : "+_bodyId);
        System.out.println("_distanceFromArrivalLS : "+_distanceFromArrivalLS);
        System.out.println("_starType : "+_starType);
        System.out.println("_stellarMass : "+_stellarMass);
        System.out.println("_radius : "+_radius);
        System.out.println("_absoluteMagnitude : "+_absoluteMagnitude);
        System.out.println("_age_MY : "+_age_MY);
        System.out.println("_surfaceTemperature : "+_surfaceTemperature);
        System.out.println("_luminosity : "+_luminosity);
        System.out.println("_rotationPeriod : "+_rotationPeriod);
        System.out.println("_axialTilt : "+_axialTilt);
    }
}
