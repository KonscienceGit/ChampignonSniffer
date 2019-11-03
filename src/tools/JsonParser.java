package tools;

import tools.exceptions.IllegalJsonFormatException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IllegalFormatException;
import java.util.List;

public abstract class JsonParser {

    private static String getCategoryTag(String catName){
        return "\""+catName+"\":";
    }

    private static int getIndexOfCatTag(String catTag, String line) throws NoSuchFieldException {
        return getIndexOfCatTag(catTag, line, 0);
    }

    private static int getIndexOfCatTag(String catTag, String line, int offset) throws NoSuchFieldException {
        int pos = line.indexOf(catTag, offset);
        if (pos != -1 ) {
            return pos;
        } else{
            throw new NoSuchFieldException("Line: " + getLineNumber() + "Couldn't find category: " + catTag);
        }
    }

    public static String getStrContentOf(String catName, String line) throws NoSuchFieldException {
        return getStrContentOf(catName, line, 0);
    }

    public static String getStrContentOf(String catName, String line, int offset) throws NoSuchFieldException {
        String catTag = getCategoryTag(catName);
        int posCat = getIndexOfCatTag(catTag, line, offset);
        int posBegin = posCat + catTag.length() +1;
        int posEnd = line.indexOf('\"', posBegin);
        return line.substring(posBegin, posEnd);
    }

    public static String getObject(String catName, String line) throws NoSuchFieldException, IllegalFormatException {
        String catTag = getCategoryTag(catName);
        int posCat = getIndexOfCatTag(catTag, line);
        int posBegin = posCat + catTag.length();
        return getObject(line, posBegin);
    }

    public static String getObject(String line, int posBegin){
        int curlyBracketToClose = 0;
        int objBegin = 0;
        boolean objBeginFound = false;
        int objEnd = 0;
        for (int i = posBegin; i < line.length(); i++){
            if (line.charAt(i) == '{'){
                if(!objBeginFound){
                    objBeginFound = true;
                    objBegin = i;
                }
                curlyBracketToClose++;
            } else if(objBeginFound && line.charAt(i) == '}'){
                curlyBracketToClose--;
                if (curlyBracketToClose == 0){
                    objEnd = i;
                    break;
                }
            }
        }
        if(!objBeginFound){
            return "";
        }else if(objEnd == 0) {
            throw new IllegalJsonFormatException("Couldn't find the closing bracket \"}\" of the JSON object");
        }
        return line.substring(objBegin, objEnd+1);
    }

    public static String[] getArray(String catName, String line) throws NoSuchFieldException, IllegalFormatException {
        List<String> objectArray = new ArrayList<>();
        String arrayContent = getArrayContentSubString(catName, line);
        boolean continueToExtract = true;
        int pos = 0;
        while (continueToExtract){
            String object = getObject(arrayContent, pos);
            if (object.isEmpty()){
                continueToExtract = false;
            }else{
                objectArray.add(object);
                pos += object.length();
            }
        }
        String[] result = new String[objectArray.size()];
        return objectArray.toArray(result);
    }

    private static String getArrayContentSubString(String catName, String line) throws NoSuchFieldException, IllegalFormatException {
        String catTag = getCategoryTag(catName);
        int posCat = getIndexOfCatTag(catTag, line);
        int posBegin = posCat + catTag.length();
        int squareBracketToClose = 0;
        int posEnd = 0;
        for (int i = posBegin; i < line.length(); i++){
            if (line.charAt(i) == '['){
                squareBracketToClose++;
            } else if(line.charAt(i) == ']'){
                squareBracketToClose--;
                if (squareBracketToClose == 0){
                    posEnd = i;
                    break;
                }
            }
        }
        if (posEnd == 0) {
            throw new IllegalJsonFormatException("Couldn't find the closing bracket \"]\" of the JSON array");
        }
        return line.substring(posBegin, posEnd);
    }

    /**
     * @param array the array to parse
     * @param fieldName the name to seek in the line to extract.
     * @return Return the first array line holding a field named as the given parameter. Return an empty string if not found.
     */
    public static String getLineInArray(String[] array, String fieldName){
        return getLineInArrayWithMentionOf(array,"\""+fieldName+"\":");
    }

    /**
     * @param array the array to parse
     * @param mention the chain of character to seek in the line to extract.
     * @return Return the first array line holding the given parameter. Return an empty string if not found.
     */
    public static String getLineInArrayWithMentionOf(String[] array, String mention){
        for (String line: array){
            if (line.contains(mention)){
                return line;
            }
        }
        return "";
    }

    public static float getFloatContentOf(String catName, String line){
        catName = "\""+catName+"\":";
        int pos = line.indexOf(catName);
        if (pos != -1 ) {
            int posBegin = pos + catName.length();
            int posEnd = line.indexOf(',', posBegin);
            if(posEnd == -1){posEnd= line.indexOf(' ', posBegin);}
            if(posEnd == -1){posEnd= line.indexOf('}', posBegin);}
            return Float.parseFloat(line.substring(posBegin, posEnd));
        }else{
            printStack();
            System.out.println("Line: " +getLineNumber()+" getFloatContentOf: Parsing error??");
            return 0.0f;
        }
    }

    /**
     * @param catName Category Name
     * @param line Json Line
     */
    public static int getIntContentOf(String catName, String line){
        catName = "\""+catName+"\":";
        int pos = line.indexOf(catName);
        if (pos != -1 ) {
            int posBegin = pos + catName.length();
            int posEnd = line.indexOf(',', posBegin);
            if(posEnd == -1){posEnd= line.indexOf(' ', posBegin);}
            if(posEnd == -1){posEnd= line.indexOf('}', posBegin);}
            return Integer.parseInt(line.substring(posBegin, posEnd));
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
            return Long.parseLong(line.substring(posBegin, posEnd));
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
            return Double.parseDouble(line.substring(posBegin, posEnd));
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
            result[0] = Double.parseDouble(line.substring(posBegin, posEnd));
            posBegin = posEnd +1;
            posEnd = line.indexOf(',', posBegin);
            result[1] = Double.parseDouble(line.substring(posBegin, posEnd));
            posBegin = posEnd +1;
            posEnd = line.indexOf(']', posBegin);
            result[2] = Double.parseDouble(line.substring(posBegin, posEnd));
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
    /**
     * @return int - Current line number.
     */
    private static int getLineNumber() {
        return Thread.currentThread().getStackTrace()[2].getLineNumber();
    }

    /** Get the last trace line.
     */
    private static void printStack() {
        String stack = Arrays.toString(Thread.currentThread().getStackTrace());
        System.out.println(stack);
    }

}
