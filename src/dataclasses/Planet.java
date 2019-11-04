package dataclasses;

import static tools.JsonParser.*;

public class Planet extends AstronomicalBody {
    public float _distanceFromArrivalLS = 0.0f;
    public boolean _tidalLock = true;
    public String _terraformState = "";
    public String _planetClass = "";
    public String _atmosphere = "";
    public String _atmosphereType = "";
    public String _atmosphereComposition = "";
    public String _volcanism = "";
    public float _massEM = 0.0f;
    public double _radius = 0.0f;
    public double _surfaceGravity = 0.0d;
    public double _surfaceTemperature = 0.0f;
    public double _surfacePressure = 0.0d;
    public boolean _landable = false;
    public String _materials = "";
    public String _composition = "";
    public double _semiMajorAxis = 0.0d;
    public float _eccentricity = 0.0f;
    public float _orbitalInclination = 0.0f;
    public double _periapsis = 0.0d;
    public double _orbitalPeriod = 0.0d;
    public double _rotationPeriod = 0.0f;
    public float _axialTilt = 0.0f;

    public boolean _isGasGiant = false;


    public Planet (String line) throws NoSuchFieldException {
        _detailedScanContent = line;
        _bodyType = BODY_PLANET;
        _bodyId = getIntContentOf("BodyID",line);

        _bodyName = getStrContentOf("BodyName",line);
        _terraformState = getStrContentOf("TerraformState",line);
        _planetClass = getStrContentOf("PlanetClass",line);
        _atmosphere = getStrContentOf("Atmosphere",line);
        if (!_isGasGiant) {
            _atmosphereType = getStrContentOf("AtmosphereType",line);
        }
        _volcanism = getStrContentOf("Volcanism",line);

        _distanceFromArrivalLS = getFloatContentOf("DistanceFromArrivalLS",line);

        _tidalLock = getBoolContentOf("TidalLock",line);
        _isGasGiant = _planetClass.contains("gas giant");


        _atmosphereComposition = getAtmosCompoOf(line);
        _massEM = getFloatContentOf("MassEM",line);

        _radius = getDoubleContentOf("Radius",line);

        _surfaceGravity = getDoubleContentOf("SurfaceGravity",line);

        _surfaceTemperature = getDoubleContentOf("SurfaceTemperature",line);

        _surfacePressure = getDoubleContentOf("SurfacePressure",line);
        _landable = getBoolContentOf("Landable",line);

        _materials = getMaterialsCompoOf(line);

        if (!_isGasGiant) {
            _composition = getGroundCompoOf(line);
        }

        _semiMajorAxis = getDoubleContentOf("SemiMajorAxis",line);
        _eccentricity = getFloatContentOf("Eccentricity",line);
        _orbitalInclination = getFloatContentOf("OrbitalInclination",line);
        _periapsis = getDoubleContentOf("Periapsis",line);
        _orbitalPeriod = getDoubleContentOf("OrbitalPeriod",line);

        _rotationPeriod = getDoubleContentOf("RotationPeriod",line);
        _axialTilt = getFloatContentOf("AxialTilt",line);
    }

    public void print(){
        System.out.println();
        System.out.println("/************BODY PLANET************/");
        System.out.println("_bodyType : "+_bodyType);
        System.out.println("_bodyName : "+_bodyName);
        System.out.println("_bodyId : "+_bodyId);
        System.out.println("_distanceFromArrivalLS : "+_distanceFromArrivalLS);

        System.out.println("_tidalLock : "+_tidalLock);
        System.out.println("_terraformState : "+_terraformState);
        System.out.println("_planetClass : "+_planetClass);
        System.out.println("_atmosphere : "+_atmosphere);
        System.out.println("_atmosphereType : "+_atmosphereType);
        System.out.println("_atmosphereComposition : "+_atmosphereComposition);
        System.out.println("_volcanism : "+_volcanism);
        System.out.println("_massEM : "+_massEM);

        System.out.println("_radius : "+_radius);

        System.out.println("_surfaceGravity : "+_surfaceGravity);
        System.out.println("_surfaceTemperature : "+_surfaceTemperature);

        System.out.println("_surfacePressure : "+_surfacePressure);
        System.out.println("_landable : "+_landable);
        System.out.println("_materials : "+_materials);
        System.out.println("_composition : "+_composition);
        System.out.println("_semiMajorAxis : "+_semiMajorAxis);
        System.out.println("_eccentricity : "+_eccentricity);
        System.out.println("_orbitalInclination : "+_orbitalInclination);
        System.out.println("_periapsis : "+_periapsis);
        System.out.println("_orbitalPeriod : "+_orbitalPeriod);

        System.out.println("_rotationPeriod : "+_rotationPeriod);
        System.out.println("_axialTilt : "+_axialTilt);
    }
}
