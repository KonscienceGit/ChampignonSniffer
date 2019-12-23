package dataclasses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Module {
    private String slot;
    private String item;
    private boolean on;
    private int priority;
    private int ammoInClip;
    private int ammoInHopper;
    private float health;
    private Engineering engineering;

    @JsonProperty("Slot")
    public String getSlot() {
        return slot;
    }

    @JsonProperty("Slot")
    public void setSlot(String slot) {
        this.slot = slot;
    }

    @JsonProperty("Item")
    public String getItem() {
        return item;
    }

    @JsonProperty("Item")
    public void setItem(String item) {
        this.item = item;
    }

    @JsonProperty("On")
    public boolean isOn() {
        return on;
    }

    @JsonProperty("On")
    public void setOn(boolean on) {
        this.on = on;
    }

    @JsonProperty("Priority")
    public int getPriority() {
        return priority;
    }

    @JsonProperty("Priority")
    public void setPriority(int priority) {
        this.priority = priority;
    }

    @JsonProperty("AmmoInClip")
    public int getAmmoInClip() {
        return ammoInClip;
    }

    @JsonProperty("AmmoInClip")
    public void setAmmoInClip(int ammoInClip) {
        this.ammoInClip = ammoInClip;
    }

    @JsonProperty("AmmoInHopper")
    public int getAmmoInHopper() {
        return ammoInHopper;
    }

    @JsonProperty("AmmoInHopper")
    public void setAmmoInHopper(int ammoInHopper) {
        this.ammoInHopper = ammoInHopper;
    }

    @JsonProperty("Health")
    public float getHealth() {
        return health;
    }

    @JsonProperty("Health")
    public void setHealth(float health) {
        this.health = health;
    }

    @JsonProperty("Engineering")
    public Engineering getEngineering() {
        return engineering;
    }

    @JsonProperty("Engineering")
    public void setEngineering(Engineering engineering) {
        this.engineering = engineering;
    }

    public static class Engineering{
        private String engineer;
        private String engineerID;
        private String blueprintID;
        private String blueprintName;
        private int level;
        private float quality;
        private String experimentalEffect;
        private String experimentalEffect_Localized;
        private Modifier[] modifiers;

        @JsonProperty("Engineer")
        public String getEngineer() {
            return engineer;
        }

        @JsonProperty("Engineer")
        public void setEngineer(String engineer) {
            this.engineer = engineer;
        }

        @JsonProperty("EngineerID")
        public String getEngineerID() {
            return engineerID;
        }

        @JsonProperty("EngineerID")
        public void setEngineerID(String engineerID) {
            this.engineerID = engineerID;
        }

        @JsonProperty("BlueprintID")
        public String getBlueprintID() {
            return blueprintID;
        }

        @JsonProperty("BlueprintID")
        public void setBlueprintID(String blueprintID) {
            this.blueprintID = blueprintID;
        }

        @JsonProperty("BlueprintName")
        public String getBlueprintName() {
            return blueprintName;
        }

        @JsonProperty("BlueprintName")
        public void setBlueprintName(String blueprintName) {
            this.blueprintName = blueprintName;
        }

        @JsonProperty("Level")
        public int getLevel() {
            return level;
        }

        @JsonProperty("Level")
        public void setLevel(int level) {
            this.level = level;
        }

        @JsonProperty("Quality")
        public float getQuality() {
            return quality;
        }

        @JsonProperty("Quality")
        public void setQuality(float quality) {
            this.quality = quality;
        }

        @JsonProperty("ExperimentalEffect")
        public String getExperimentalEffect() {
            return experimentalEffect;
        }

        @JsonProperty("ExperimentalEffect")
        public void setExperimentalEffect(String experimentalEffect) {
            this.experimentalEffect = experimentalEffect;
        }

        @JsonProperty("ExperimentalEffect_Localised")
        public String getExperimentalEffect_Localized() {
            return experimentalEffect_Localized;
        }

        @JsonProperty("ExperimentalEffect_Localised")
        public void setExperimentalEffect_Localized(String experimentalEffect_Localized) {
            this.experimentalEffect_Localized = experimentalEffect_Localized;
        }

        @JsonProperty("Modifiers")
        public Modifier[] getModifiers() {
            return modifiers;
        }

        @JsonProperty("Modifiers")
        public void setModifiers(Modifier[] modifiers) {
            this.modifiers = modifiers;
        }

        public static class Modifier{
            private String label;
            private float value;
            private float originalValue;
            private int lessIsGood;

            @JsonProperty("Label")
            public String getLabel() {
                return label;
            }

            @JsonProperty("Label")
            public void setLabel(String label) {
                this.label = label;
            }

            @JsonProperty("Value")
            public float getValue() {
                return value;
            }

            @JsonProperty("Value")
            public void setValue(float value) {
                this.value = value;
            }

            @JsonProperty("OriginalValue")
            public float getOriginalValue() {
                return originalValue;
            }

            @JsonProperty("OriginalValue")
            public void setOriginalValue(float originalValue) {
                this.originalValue = originalValue;
            }

            @JsonProperty("LessIsGood")
            public int getLessIsGood() {
                return lessIsGood;
            }

            @JsonProperty("LessIsGood")
            public void setLessIsGood(int lessIsGood) {
                this.lessIsGood = lessIsGood;
            }
        }


    }
}
