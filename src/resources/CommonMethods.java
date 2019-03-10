package resources;

import java.util.Arrays;

public abstract class CommonMethods {

    //Parsing text
    public static String getStrContentOf(String catName, String line){
        catName = "\""+catName+"\":\"";
        int pos = line.indexOf(catName);
        if (pos != -1 ) {
            int posBegin = pos + catName.length();
            int posEnd = line.indexOf('\"', posBegin);
            return line.substring(posBegin, posEnd);
        }else{
            printStack();
            System.out.println("Line: " +getLineNumber()+" getStrContentOf: Parsing error??");
            return "";
        }
    }

    public static String getFromIndexStrContentOf(String catName, String line, int offset){
        catName = "\""+catName+"\":\"";
        int pos = line.indexOf(catName, offset);
        if (pos != -1 ) {
            int posBegin = pos + catName.length();
            int posEnd = line.indexOf('\"', posBegin);
            return line.substring(posBegin, posEnd);
        }else{
            printStack();
            System.out.println("Line: " +getLineNumber()+" getFromIndexStrContentOf: Parsing error??");
            return "";
        }
    }

    public static float getFloatContentOf(String catName, String line){
        catName = "\""+catName+"\":";
        int pos = line.indexOf(catName);
        if (pos != -1 ) {
            int posBegin = pos + catName.length();
            int posEnd = line.indexOf(',', posBegin);
            if(posEnd == -1){posEnd= line.indexOf(' ', posBegin);}
            if(posEnd == -1){posEnd= line.indexOf('}', posBegin);}
            return Float.valueOf(line.substring(posBegin, posEnd));
        }else{
            printStack();
            System.out.println("Line: " +getLineNumber()+" getFloatContentOf: Parsing error??");
            return 0.0f;
        }
    }

    public static int getIntContentOf(String catName, String line){
        catName = "\""+catName+"\":";
        int pos = line.indexOf(catName);
        if (pos != -1 ) {
            int posBegin = pos + catName.length();
            int posEnd = line.indexOf(',', posBegin);
            if(posEnd == -1){posEnd= line.indexOf(' ', posBegin);}
            if(posEnd == -1){posEnd= line.indexOf('}', posBegin);}
            return Integer.valueOf(line.substring(posBegin, posEnd));
        }else{
            printStack();
            System.out.println("Line: " +getLineNumber()+" getIntContentOf: Parsing error??");
            return 0;
        }
    }

    public static long getLongContentOf(String catName, String line){
        catName = "\""+catName+"\":";
        int pos = line.indexOf(catName);
        if (pos != -1 ) {
            int posBegin = pos + catName.length();
            int posEnd = line.indexOf(',', posBegin);
            if(posEnd == -1){posEnd= line.indexOf(' ', posBegin);}
            if(posEnd == -1){posEnd= line.indexOf('}', posBegin);}
            return Long.valueOf(line.substring(posBegin, posEnd));
        }else{
            printStack();
            System.out.println("Line: " +getLineNumber()+" getLongContentOf: Parsing error??");
            return 0;
        }
    }

    public static double getDoubleContentOf(String catName, String line){
        catName = "\""+catName+"\":";
        int pos = line.indexOf(catName);
        if (pos != -1 ) {
            int posBegin = pos + catName.length();
            int posEnd = line.indexOf(',', posBegin);
            if(posEnd == -1){posEnd= line.indexOf(' ', posBegin);}
            if(posEnd == -1){posEnd= line.indexOf('}', posBegin);}
            return Double.valueOf(line.substring(posBegin, posEnd));
        }else{
            printStack();
            System.out.println("Line: " +getLineNumber()+" getDoubleContentOf: Parsing error??");
            return 0.0d;
        }
    }

    public static boolean getBoolContentOf(String catName, String line){
        catName = "\""+catName+"\":";
        int pos = line.indexOf(catName);
        if (pos != -1 ) {
            int posBegin = pos + catName.length();
            int posEnd = line.indexOf(',', posBegin);
            if(posEnd == -1){posEnd= line.indexOf(' ', posBegin);}
            if(posEnd == -1){posEnd= line.indexOf('}', posBegin);}
            return line.substring(posBegin, posEnd).contains("true");
        }else{
            printStack();
            System.out.println("Line: " +getLineNumber()+" getBoolContentOf: Parsing error??");
            return false;
        }
    }

    public static double[] getStarPos(String line){
        //"StarPos":[-262.03125,-420.71875,580.37500],
        String catName = "StarPos";
        int pos = line.indexOf(catName);
        if (pos != -1 ) {
            double[] result = new double[3];
            int posBegin = line.indexOf("[")+1;
            int posEnd = line.indexOf(',', posBegin);
            result[0] = Double.valueOf(line.substring(posBegin, posEnd));
            posBegin = posEnd +1;
            posEnd = line.indexOf(',', posBegin);
            result[1] = Double.valueOf(line.substring(posBegin, posEnd));
            posBegin = posEnd +1;
            posEnd = line.indexOf(']', posBegin);
            result[2] = Double.valueOf(line.substring(posBegin, posEnd));
            return result;
        }else{
            printStack();
            System.out.println("Line: " +getLineNumber()+" StarPos parsing error??");
            return new double[]{0.0d,0.0d,0.0d};
        }
    }

    public static String getAtmosCompoOf(String line){
        String catName = "\"AtmosphereComposition\":[";
        int pos = line.indexOf(catName);
        if (pos != -1 ) {
            int posBegin = pos + catName.length();
            int posEnd = line.indexOf(']', posBegin);
            return line.substring(posBegin, posEnd);
        }else{
            return "";
        }
    }

    public static String getGroundCompoOf(String line){
        String catName = "\"Composition\":{";
        int pos = line.indexOf(catName);
        if (pos != -1 ) {
            int posBegin = pos + catName.length();
            int posEnd = line.indexOf('}', posBegin);
            return line.substring(posBegin, posEnd);
        }else{
            printStack();
            System.out.println("Line: " +getLineNumber()+" getGroundCompoOf: Parsing error??");
            return "";
        }
    }

    public static String getMaterialsCompoOf(String line){
        String catName = "\"Materials\":[";
        int pos = line.indexOf(catName);
        if (pos != -1 ) {
            int posBegin = pos + catName.length();
            int posEnd = line.indexOf(']', posBegin);
            return line.substring(posBegin, posEnd);
        }else{
            return "";
        }
    }

    //Debug
    /** Get the current line number.
     * @return int - Current line number.
     */
    public static int getLineNumber() {
        return Thread.currentThread().getStackTrace()[2].getLineNumber();
    }

    /** Get the last trace line.
     * @return String - Last stack trace.
     */
    public static void printStack() {
        String stack = Arrays.toString(Thread.currentThread().getStackTrace());
        System.out.println(stack);
    }

}
