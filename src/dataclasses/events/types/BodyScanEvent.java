package dataclasses.events.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import dataclasses.GameSession;
import dataclasses.astronomy.Composition;
import dataclasses.astronomy.Material;
import dataclasses.events.Event;
import gui.ChampignonScreenJFrame;
import tools.ColorConverter;
import tools.Constants;
import tools.exceptions.NotABodyException;

import java.awt.*;

public class BodyScanEvent extends Event {
    private String scanType;
    private String bodyName;
    private int bodyID;
    private BodyParent[] parents;
    private String starSystem;
    private long systemAddress;
    private float distanceFromArrivalLS;

    //Solid body
    private boolean tidalLock;
    private String terraformState;
    private String volcanism;
    private float massEM;
    private float surfaceGravity;
    private float surfacePressure;
    private boolean landable = false;
    private Material[] materials;
    private Composition composition;
    //?
    private String planetClass;
    private String atmosphere;
    private String atmosphereType;//Empty str if no atm
    //With atmosphere
    private Material[] atmosphereComposition;

    //Star
    private String starType;
    private int starSubClass;
    private float stellarMass;
    private String luminosity;
    private float absoluteMagnitude;
    private long age_MY;

    private long radius;
    private long surfaceTemperature;
    private long semiMajorAxis;
    private float eccentricity;
    private float orbitalInclination;
    private float periapsis;
    private long orbitalPeriod;
    private float rotationPeriod;
    private float axialTilt;
    private Ring[] rings;
    private String reserveLevel;
    private boolean wasDiscovered;
    private boolean wasMapped;

    //Customs
    private boolean isStar = false;
    private boolean isCluster = false;
    private boolean isGasGiant = false;
    private boolean isPlanet = false;
    private boolean scoopable = false;
    private Color starColor;

    @Override
    public void updateContext(GameSession gameSession) {
        gameSession.getCurrentStarSystem().addBody(this);
//        this.setNeedUpdateGUI(true);
    }

    public boolean isPlanet() {
        return isPlanet;
    }

    public boolean isCluster() {
        return isCluster;
    }

    public boolean isGasGiant() {
        return isGasGiant;
    }

    @JsonProperty("ScanType")
    public String getScanType() {
        return scanType;
    }

    @JsonProperty("ScanType")
    public void setScanType(String scanType) {
        this.scanType = scanType;
    }

    @JsonProperty("BodyName")
    public String getBodyName() {
        return bodyName;
    }

    @JsonProperty("BodyName")
    public void setBodyName(String bodyName) {
        this.bodyName = bodyName;
        if(!bodyName.isBlank()){
            isCluster = bodyName.toLowerCase().contains("cluster");
        }
    }

    @JsonProperty("BodyID")
    public int getBodyID() {
        return bodyID;
    }

    public int getBodyIDWithCheck() throws NotABodyException {
        if (bodyID != -10){
            return bodyID;
        }else{
            throw new NotABodyException("This Scan ain't scanned no body", this);
        }
    }

    @JsonProperty("Parents")
    public BodyParent[] getParents() {
        return parents;
    }

    @JsonProperty("Parents")
    public void setParents(BodyParent[] parents) {
        this.parents = parents;
    }

    @JsonProperty("BodyID")
    public void setBodyID(int bodyID) {
        this.bodyID = bodyID;
    }

    @JsonProperty("StarSystem")
    public String getStarSystem() {
        return starSystem;
    }

    @JsonProperty("StarSystem")
    public void setStarSystem(String starSystem) {
        this.starSystem = starSystem;
    }

    @JsonProperty("SystemAddress")
    public long getSystemAddress() {
        return systemAddress;
    }

    @JsonProperty("SystemAddress")
    public void setSystemAddress(long systemAddress) {
        this.systemAddress = systemAddress;
    }

    @JsonProperty("DistanceFromArrivalLS")
    public float getDistanceFromArrivalLS() {
        return distanceFromArrivalLS;
    }

    @JsonProperty("DistanceFromArrivalLS")
    public void setDistanceFromArrivalLS(float distanceFromArrivalLS) {
        this.distanceFromArrivalLS = distanceFromArrivalLS;
    }

    @JsonProperty("TidalLock")
    public boolean isTidalLock() {
        return tidalLock;
    }

    @JsonProperty("TidalLock")
    public void setTidalLock(boolean tidalLock) {
        this.tidalLock = tidalLock;
    }

    @JsonProperty("StarType")
    public String getStarType() {
        return starType;
    }

    public boolean isScoopable() {
        return scoopable;
    }

    public void setScoopable(boolean scoopable) {
        this.scoopable = scoopable;
    }

    @JsonProperty("StarType")
    public void setStarType(String starType) {
        this.starType = starType;
        if(!starType.isBlank()){
            isStar = true;
            setScoopable(Constants.isScoopable(starType));
        }
    }

    public boolean isStar() {
        return isStar;
    }

    @JsonProperty("Subclass")
    public int getStarSubClass() {
        return starSubClass;
    }

    @JsonProperty("Subclass")
    public void setStarSubClass(int starSubClass) {
        this.starSubClass = starSubClass;
    }

    @JsonProperty("StellarMass")
    public float getStellarMass() {
        return stellarMass;
    }

    @JsonProperty("StellarMass")
    public void setStellarMass(float stellarMass) {
        this.stellarMass = stellarMass;
    }

    @JsonProperty("Radius")
    public long getRadius() {
        return radius;
    }

    @JsonProperty("Radius")
    public void setRadius(long radius) {
        this.radius = radius;
    }

    @JsonProperty("AbsoluteMagnitude")
    public float getAbsoluteMagnitude() {
        return absoluteMagnitude;
    }

    @JsonProperty("AbsoluteMagnitude")
    public void setAbsoluteMagnitude(float absoluteMagnitude) {
        this.absoluteMagnitude = absoluteMagnitude;
    }

    @JsonProperty("Age_MY")
    public long getAge_MY() {
        return age_MY;
    }

    @JsonProperty("Age_MY")
    public void setAge_MY(long age_MY) {
        this.age_MY = age_MY;
    }

    @JsonProperty("SurfaceTemperature")
    public long getSurfaceTemperature() {
        return surfaceTemperature;
    }

    @JsonProperty("SurfaceTemperature")
    public void setSurfaceTemperature(long surfaceTemperature) {
        this.surfaceTemperature = surfaceTemperature;
        starColor = ColorConverter.getColor(surfaceTemperature);
    }

    @JsonProperty("Luminosity")
    public String getLuminosity() {
        return luminosity;
    }

    @JsonProperty("Luminosity")
    public void setLuminosity(String luminosity) {
        this.luminosity = luminosity;
    }

    @JsonProperty("SemiMajorAxis")
    public long getSemiMajorAxis() {
        return semiMajorAxis;
    }

    @JsonProperty("SemiMajorAxis")
    public void setSemiMajorAxis(long semiMajorAxis) {
        this.semiMajorAxis = semiMajorAxis;
    }

    @JsonProperty("Eccentricity")
    public float getEccentricity() {
        return eccentricity;
    }

    @JsonProperty("Eccentricity")
    public void setEccentricity(float eccentricity) {
        this.eccentricity = eccentricity;
    }

    @JsonProperty("OrbitalInclination")
    public float getOrbitalInclination() {
        return orbitalInclination;
    }

    @JsonProperty("OrbitalInclination")
    public void setOrbitalInclination(float orbitalInclination) {
        this.orbitalInclination = orbitalInclination;
    }

    @JsonProperty("Periapsis")
    public float getPeriapsis() {
        return periapsis;
    }

    @JsonProperty("Periapsis")
    public void setPeriapsis(float periapsis) {
        this.periapsis = periapsis;
    }

    @JsonProperty("OrbitalPeriod")
    public long getOrbitalPeriod() {
        return orbitalPeriod;
    }

    @JsonProperty("OrbitalPeriod")
    public void setOrbitalPeriod(long orbitalPeriod) {
        this.orbitalPeriod = orbitalPeriod;
    }

    @JsonProperty("RotationPeriod")
    public float getRotationPeriod() {
        return rotationPeriod;
    }

    @JsonProperty("RotationPeriod")
    public void setRotationPeriod(float rotationPeriod) {
        this.rotationPeriod = rotationPeriod;
    }

    @JsonProperty("AxialTilt")
    public float getAxialTilt() {
        return axialTilt;
    }

    @JsonProperty("AxialTilt")
    public void setAxialTilt(float axialTilt) {
        this.axialTilt = axialTilt;
    }

    @JsonProperty("Rings")
    public Ring[] getRings() {
        return rings;
    }

    @JsonProperty("Rings")
    public void setRings(Ring[] rings) {
        this.rings = rings;
    }

    @JsonProperty("AtmosphereComposition")
    public Material[] getAtmosphereComposition() {
        return atmosphereComposition;
    }

    @JsonProperty("AtmosphereComposition")
    public void setAtmosphereComposition(Material[] atmosphereComposition) {
        this.atmosphereComposition = atmosphereComposition;
    }

    @JsonProperty("ReserveLevel")
    public String getReserveLevel() {
        return reserveLevel;
    }

    @JsonProperty("ReserveLevel")
    public void setReserveLevel(String reserveLevel) {
        this.reserveLevel = reserveLevel;
    }

    @JsonProperty("WasDiscovered")
    public boolean isWasDiscovered() {
        return wasDiscovered;
    }

    @JsonProperty("WasDiscovered")
    public void setWasDiscovered(boolean wasDiscovered) {
        this.wasDiscovered = wasDiscovered;
    }

    @JsonProperty("WasMapped")
    public boolean isWasMapped() {
        return wasMapped;
    }

    @JsonProperty("WasMapped")
    public void setWasMapped(boolean wasMapped) {
        this.wasMapped = wasMapped;
    }

    @JsonProperty("TerraformState")
    public String getTerraformState() {
        return terraformState;
    }

    @JsonProperty("TerraformState")
    public void setTerraformState(String terraformState) {
        this.terraformState = terraformState;
    }

    @JsonProperty("Volcanism")
    public String getVolcanism() {
        return volcanism;
    }

    @JsonProperty("Volcanism")
    public void setVolcanism(String volcanism) {
        this.volcanism = volcanism;
    }

    @JsonProperty("MassEM")
    public float getMassEM() {
        return massEM;
    }

    @JsonProperty("MassEM")
    public void setMassEM(float massEM) {
        this.massEM = massEM;
    }

    @JsonProperty("SurfaceGravity")
    public float getSurfaceGravity() {
        return surfaceGravity;
    }

    @JsonProperty("SurfaceGravity")
    public void setSurfaceGravity(float surfaceGravity) {
        this.surfaceGravity = surfaceGravity;
    }

    @JsonProperty("SurfacePressure")
    public float getSurfacePressure() {
        return surfacePressure;
    }

    @JsonProperty("SurfacePressure")
    public void setSurfacePressure(float surfacePressure) {
        this.surfacePressure = surfacePressure;
    }

    @JsonProperty("Landable")
    public boolean isLandable() {
        return landable;
    }

    @JsonProperty("Landable")
    public void setLandable(boolean landable) {
        this.landable = landable;
    }

    @JsonProperty("Materials")
    public Material[] getMaterials() {
        return materials;
    }

    @JsonProperty("Materials")
    public void setMaterials(Material[] materials) {
        this.materials = materials;
    }

    @JsonProperty("Composition")
    public Composition getComposition() {
        return composition;
    }

    @JsonProperty("Composition")
    public void setComposition(Composition composition) {
        this.composition = composition;
    }

    @JsonProperty("PlanetClass")
    public String getPlanetClass() {
        return planetClass;
    }

    @JsonProperty("PlanetClass")
    public void setPlanetClass(String planetClassArg) {
        this.planetClass = planetClassArg;
        if(!planetClassArg.isBlank()){
            planetClassArg = planetClassArg.toLowerCase();
            isGasGiant = (planetClassArg.contains("gas") && planetClassArg.contains("giant"));
            isPlanet = planetClassArg.contains("body");
        }
    }

    @JsonProperty("Atmosphere")
    public String getAtmosphere() {
        return atmosphere;
    }

    @JsonProperty("Atmosphere")
    public void setAtmosphere(String atmosphere) {
        this.atmosphere = atmosphere;
    }

    @JsonProperty("AtmosphereType")
    public String getAtmosphereType() {
        return atmosphereType;
    }

    @JsonProperty("AtmosphereType")
    public void setAtmosphereType(String atmosphereType) {
        this.atmosphereType = atmosphereType;
    }

    public Color getStarColor() {
        return starColor;
    }

    public static class BodyParent{
        private int _null = -1;
        private int ring = -1;
        private int star = -1;
        private int planet = -1;

        @JsonProperty("Null")
        public int get_null() {
            return _null;
        }

        @JsonProperty("Null")
        public void set_null(int _null) {
            this._null = _null;
        }

        @JsonProperty("Ring")
        public int getRing() {
            return ring;
        }

        @JsonProperty("Ring")
        public void setRing(int ring) {
            this.ring = ring;
        }

        @JsonProperty("Star")
        public int getStar() {
            return star;
        }

        @JsonProperty("Star")
        public void setStar(int star) {
            this.star = star;
        }

        @JsonProperty("Planet")
        public int getPlanet() {
            return planet;
        }

        @JsonProperty("Planet")
        public void setPlanet(int planet) {
            this.planet = planet;
        }
    }

    public static class Ring {
        private String name;
        private String ringClass;
        private float massMT;
        private float innerRadius;
        private float outerRadius;

        @JsonProperty("Name")
        public String getName() {
            return name;
        }

        @JsonProperty("Name")
        public void setName(String name) {
            this.name = name;
        }

        @JsonProperty("RingClass")
        public String getRingClass() {
            return ringClass;
        }

        @JsonProperty("RingClass")
        public void setRingClass(String ringClass) {
            this.ringClass = ringClass;
        }

        @JsonProperty("MassMT")
        public float getMassMT() {
            return massMT;
        }

        @JsonProperty("MassMT")
        public void setMassMT(float massMT) {
            this.massMT = massMT;
        }

        @JsonProperty("InnerRad")
        public float getInnerRadius() {
            return innerRadius;
        }

        @JsonProperty("InnerRad")
        public void setInnerRadius(float innerRadius) {
            this.innerRadius = innerRadius;
        }

        @JsonProperty("OuterRad")
        public float getOuterRadius() {
            return outerRadius;
        }

        @JsonProperty("OuterRad")
        public void setOuterRadius(float outerRadius) {
            this.outerRadius = outerRadius;
        }
    }
}
