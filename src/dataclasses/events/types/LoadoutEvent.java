package dataclasses.events.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import dataclasses.GameSession;
import dataclasses.events.Event;
import dataclasses.Module;
import java.util.List;

public class LoadoutEvent extends Event {

    /** The raw text ship model (ex: "krait_mkii") */
    private String ship;
    /** Integer assigned to the ship, unknown purpose. */
    private int shipID;
    /** Ship nickname given by the player. */
    private String shipName;
    /** Short ship identity tag given by the player. */
    private String shipIdent;
    /** The value of the ship hull in credits. */
    private long hullValue;
    /** The value of the sum of the modules in credits. */
    private long modulesValue;
    /** The hull health, from 0.0 to 1.0. */
    private float hullHealth;
    /** The current mass with empty cargo holds. */
    private float unladenMass;
    private int cargoCapacity;
    private float maxJumpRange;
    private FuelCapacity fuelCapacity;
    private long rebuy;
    /** The list of Modules currently present on the ship. */
    private List<Module> modules;

    @Override
    public void updateContext(GameSession gameSession) {
        gameSession.setCurrentLoadout(this);
        boolean haveFuelScoop = false;
        for (Module module: modules){
            if (module.getItem().toLowerCase().contains("fuelscoop")){
                haveFuelScoop = true;
                break;
            }
        }
        gameSession.getFuelStatus().setHaveFuelScoop(haveFuelScoop);
        this.setNeedUpdateGUI(true);
    }

    @JsonProperty("Ship")
    public String getShip() {
        return ship;
    }

    @JsonProperty("Ship")
    public void setShip(String ship) {
        this.ship = ship;
    }

    @JsonProperty("ShipID")
    public int getShipID() {
        return shipID;
    }

    @JsonProperty("ShipID")
    public void setShipID(int shipID) {
        this.shipID = shipID;
    }

    @JsonProperty("ShipName")
    public String getShipName() {
        return shipName;
    }

    @JsonProperty("ShipName")
    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    @JsonProperty("ShipIdent")
    public String getShipIdent() {
        return shipIdent;
    }

    @JsonProperty("ShipIdent")
    public void setShipIdent(String shipIdent) {
        this.shipIdent = shipIdent;
    }

    @JsonProperty("HullValue")
    public long getHullValue() {
        return hullValue;
    }

    @JsonProperty("HullValue")
    public void setHullValue(long hullValue) {
        this.hullValue = hullValue;
    }

    @JsonProperty("ModulesValue")
    public long getModulesValue() {
        return modulesValue;
    }

    @JsonProperty("ModulesValue")
    public void setModulesValue(long modulesValue) {
        this.modulesValue = modulesValue;
    }

    @JsonProperty("HullHealth")
    public float getHullHealth() {
        return hullHealth;
    }

    @JsonProperty("HullHealth")
    public void setHullHealth(float hullHealth) {
        this.hullHealth = hullHealth;
    }

    @JsonProperty("UnladenMass")
    public float getUnladenMass() {
        return unladenMass;
    }

    @JsonProperty("UnladenMass")
    public void setUnladenMass(float unladenMass) {
        this.unladenMass = unladenMass;
    }

    @JsonProperty("CargoCapacity")
    public int getCargoCapacity() {
        return cargoCapacity;
    }

    @JsonProperty("CargoCapacity")
    public void setCargoCapacity(int cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    @JsonProperty("MaxJumpRange")
    public float getMaxJumpRange() {
        return maxJumpRange;
    }

    @JsonProperty("MaxJumpRange")
    public void setMaxJumpRange(float maxJumpRange) {
        this.maxJumpRange = maxJumpRange;
    }

    @JsonProperty("FuelCapacity")
    public FuelCapacity getFuelCapacity() {
        return fuelCapacity;
    }

    @JsonProperty("FuelCapacity")
    public void setFuelCapacity(FuelCapacity fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    @JsonProperty("Rebuy")
    public long getRebuy() {
        return rebuy;
    }

    @JsonProperty("Rebuy")
    public void setRebuy(long rebuy) {
        this.rebuy = rebuy;
    }

    @JsonProperty("Modules")
    public List<Module> getModules() {
        return modules;
    }

    @JsonProperty("Modules")
    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public static class FuelCapacity {
        /** The main fuel tank capacity. */
        private float Main;
        /**The reserve tank capacity/content? */
        private float Reserve;

        @JsonProperty("Main")
        public float getMain() {
            return Main;
        }

        @JsonProperty("Main")
        public void setMain(float main) {
            Main = main;
        }

        @JsonProperty("Reserve")
        public float getReserve() {
            return Reserve;
        }

        @JsonProperty("Reserve")
        public void setReserve(float reserve) {
            Reserve = reserve;
        }
    }
}