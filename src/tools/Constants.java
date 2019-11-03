package tools;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.HashMap;

public abstract class Constants {
    public static final String PROGRAM_NAME = "Champignon Sniffer 3000";
    public static final String PROGRAM_VERSION = "20191102";

    public static final Color INVISIBLE_COLOR = new Color(0, 0, 0,0);
    public static final Color TEXT_ELITE_COLOR = new Color(255, 111, 0);
    public static final Color TEXT_ELITE_TR_COLOR = new Color(255, 111, 0);
    public static final Color BACK_SCREEN_ELITE_COLOR = new Color(26, 16, 15);
    public static final Color BACK_SCREEN_ELITE_TR_COLOR = new Color(26, 16, 15, 150);
    public static final Color BLACK_ELITE_COLOR = new Color(16, 16, 16);
    public static final Color BLACK_ELITE_TR_COLOR = new Color(16, 16, 16, 10);
    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");
    public static final Font ELITE_FONT_PLAIN_28 = new Font("Dosis", Font.PLAIN, 28);

    public static final HashMap<String, Color> STAR_CLASS_COLOR_MAP = new HashMap<String, Color>(){{
            put("O",new Color(146, 181, 255));
            put("B",new Color(162, 192, 255));
            put("A",new Color(213, 224, 255));
            put("F",new Color(249, 245, 255));
            put("G",new Color(255, 237, 227));
            put("K",new Color(255, 218, 181));
            put("M",new Color(255, 155, 61));
    }};

    public static final String HYDROGEN_STAR_CLASS = "OBAFGKM";

    public static final HashMap<String, Float> FSD_FUEL_MAP = new HashMap<String, Float>(){{
        put("int_hyperdrive_size2_class1",0.60f);
        put("int_hyperdrive_size2_class2",0.60f);
        put("int_hyperdrive_size2_class3",0.60f);
        put("int_hyperdrive_size2_class4",0.80f);
        put("int_hyperdrive_size2_class5",0.90f);
        put("int_hyperdrive_size3_class1",1.20f);
        put("int_hyperdrive_size3_class2",1.20f);
        put("int_hyperdrive_size3_class3",1.20f);
        put("int_hyperdrive_size3_class4",1.50f);
        put("int_hyperdrive_size3_class5",1.80f);
        put("int_hyperdrive_size4_class1",2.00f);
        put("int_hyperdrive_size4_class2",2.00f);
        put("int_hyperdrive_size4_class3",2.00f);
        put("int_hyperdrive_size4_class4",2.50f);
        put("int_hyperdrive_size4_class5",3.00f);
        put("int_hyperdrive_size5_class1",3.30f);
        put("int_hyperdrive_size5_class2",3.30f);
        put("int_hyperdrive_size5_class3",3.30f);
        put("int_hyperdrive_size5_class4",4.10f);
        put("int_hyperdrive_size5_class5",5.00f);
        put("int_hyperdrive_size6_class1",5.30f);
        put("int_hyperdrive_size6_class2",5.30f);
        put("int_hyperdrive_size6_class3",5.30f);
        put("int_hyperdrive_size6_class4",6.60f);
        put("int_hyperdrive_size6_class5",8.00f);
        put("int_hyperdrive_size7_class1",8.50f);
        put("int_hyperdrive_size7_class2",8.50f);
        put("int_hyperdrive_size7_class3",8.50f);
        put("int_hyperdrive_size7_class4",10.60f);
        put("int_hyperdrive_size7_class5",12.80f);
    }};

    public static final class EventType {
        public static final String FILE_HEADER = "Fileheader";
        public static final String LOAD_GAME = "LoadGame";
        public static final String LOADOUT = "Loadout";
        public static final String LOCATION = "Location";
        public static final String SCAN = "Scan";

        /**
         * CMD just sectioned a FSD system target. If multiple jump to the system, the event show the number of jumps.
         */
        public static final String FSD_TARGET = "FSDTarget";

        /**
         * FSD Charging
         */
        public static final String START_JUMP = "StartJump";

        /**
         * Actual FSD jump
         */
        public static final String FSD_JUMP = "FSDJump";
        public static final String FSS_SIGNAL_DISCOVERED = "FSSSignalDiscovered";
        public static final String FSS_DISCOVERY_SCAN = "FSSDiscoveryScan";
        public static final String FUEL_SCOOP = "FuelScoop";
    }
}
