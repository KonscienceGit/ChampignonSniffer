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
    public static final HashMap<String, Float> FSD_FUEL_MAP = new HashMap<String, Float>(){
        {
        put("Int_Hyperdrive_Size2_Class1",0.60f);
        put("Int_Hyperdrive_Size2_Class2",0.60f);
        put("Int_Hyperdrive_Size2_Class3",0.60f);
        put("Int_Hyperdrive_Size2_Class4",0.80f);
        put("Int_Hyperdrive_Size2_Class5",0.90f);
        put("Int_Hyperdrive_Size3_Class1",1.20f);
        put("Int_Hyperdrive_Size3_Class2",1.20f);
        put("Int_Hyperdrive_Size3_Class3",1.20f);
        put("Int_Hyperdrive_Size3_Class4",1.50f);
        put("Int_Hyperdrive_Size3_Class5",1.80f);
        put("Int_Hyperdrive_Size4_Class1",2.00f);
        put("Int_Hyperdrive_Size4_Class2",2.00f);
        put("Int_Hyperdrive_Size4_Class3",2.00f);
        put("Int_Hyperdrive_Size4_Class4",2.50f);
        put("Int_Hyperdrive_Size4_Class5",3.00f);
        put("Int_Hyperdrive_Size5_Class1",3.30f);
        put("Int_Hyperdrive_Size5_Class2",3.30f);
        put("Int_Hyperdrive_Size5_Class3",3.30f);
        put("Int_Hyperdrive_Size5_Class4",4.10f);
        put("Int_Hyperdrive_Size5_Class5",5.00f);
        put("Int_Hyperdrive_Size6_Class1",5.30f);
        put("Int_Hyperdrive_Size6_Class2",5.30f);
        put("Int_Hyperdrive_Size6_Class3",5.30f);
        put("Int_Hyperdrive_Size6_Class4",6.60f);
        put("Int_Hyperdrive_Size6_Class5",8.00f);
        put("Int_Hyperdrive_Size7_Class1",8.50f);
        put("Int_Hyperdrive_Size7_Class2",8.50f);
        put("Int_Hyperdrive_Size7_Class3",8.50f);
        put("Int_Hyperdrive_Size7_Class4",10.60f);
        put("Int_Hyperdrive_Size7_Class5",12.80f);
        }
    };
}
