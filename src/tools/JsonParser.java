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
        int pos = line.indexOf(catTag);
        if (pos != -1 ) {
            return pos;
        } else{
            throw new NoSuchFieldException("Line: " + getLineNumber() + " Couldn't find category: " + catTag);
        }
    }

    /**
     * @param catName the name of the object ot extract.
     * @param line the JSON object from which the target object will be extracted.
     * @return the object's content enclosed by brackets {}.
     */
    public static String getObjectSubstring(String catName, String line) throws NoSuchFieldException, IllegalFormatException {
        String catTag = getCategoryTag(catName);
        int posCat = getIndexOfCatTag(catTag, line);
        int posBegin = posCat + catTag.length();
        return getObjectSubstring(line, posBegin);
    }

    /**
     * Extract the content of the first object encountered in the given line at the given position.
     * @param line the line to extract the object from.
     * @param posBegin the position at which the object extraction will start.
     * @return the JSON object content enclosed by brackets {}.
     */
    private static String getObjectSubstring(String line, int posBegin){
        return getSubstringBetweenEnclosingCharacters(line, posBegin, '{', '}');
    }

    /**
     * Extract the content of the first object array encountered in the given line at the position of the given tag.
     * @param catName the name of the array to extract.
     * @param line the line to extract the array from.
     * @return the array content as a string.
     */
    private static String getArraySubString(String catName, String line) throws NoSuchFieldException, IllegalFormatException {
        String catTag = getCategoryTag(catName);
        int posBegin = getIndexOfCatTag(catTag, line);
        return getSubstringBetweenEnclosingCharacters(line, posBegin, '[', ']');
    }

    /**
     * Extract a substring located between an opening character and a closing character, most likely either '{' and '}' or '[' and ']'"
     * @param line the string to extract the substring from.
     * @param posBegin the position at which to start extracting.
     * @param openingChar the character which delimits the start of the substring.
     * @param closingChar the character who delimits the closing of the substring, given the same amount of opening and closing character have been encountered.
     * @return the substring, without its encasing same-level pair of opening and closing characters.
     */
    private static String getSubstringBetweenEnclosingCharacters(String line, int posBegin, char openingChar, char closingChar){
        int closingCharacterToFind = 0;
        int objBegin = 0;
        boolean objBeginFound = false;
        int objEnd = 0;
        for (int i = posBegin; i < line.length(); i++){
            if (line.charAt(i) == openingChar){
                if(!objBeginFound){
                    objBeginFound = true;
                    objBegin = i +1;
                }
                closingCharacterToFind++;
            } else if(objBeginFound && line.charAt(i) == closingChar){
                closingCharacterToFind--;
                if (closingCharacterToFind == 0){
                    objEnd = i;
                    break;
                }
            }
        }
        if(!objBeginFound){
            return "";
        }else if(objEnd == 0) {
            throw new IllegalJsonFormatException("Couldn't find the closing character:" + closingChar + " of the substring to extract.");
        }
        return line.substring(objBegin, objEnd);
    }

    public static String[] getArray(String catName, String line) throws NoSuchFieldException {
        List<String> strList = new ArrayList<>();
        String currentStr = getArraySubString(catName, line);
        boolean endOfArray = false;
        while (!endOfArray){
            endOfArray = currentStr.isEmpty();
            if (!endOfArray){
                char c = getNextChar(currentStr);
                int pos = currentStr.indexOf(c);
                switch (c){
                    case ','://the array have another field
                        currentStr = currentStr.substring(pos +1);
                        break;
                    case '\"'://the next field is a String
                        String string = getStrAt(currentStr, pos);
                        pos = currentStr.indexOf(string) + string.length() +2;
                        strList.add(string);
                        currentStr = currentStr.substring(pos);
                        break;
                    case '{'://the next field is an Object
                        String object = getObjectSubstring(currentStr, pos);
                        pos = currentStr.indexOf(object) + object.length() +2;
                        strList.add(object);
                        currentStr = currentStr.substring(pos);
                        break;
                    case ' '://the space was the last character, no more field in the array.
                        endOfArray = true;
                        break;
                    default://the next field is a numeral, boolean or null value
                        String value = getAttributeSubstringAt(currentStr, pos);
                        pos = currentStr.indexOf(value) + value.length();
                        strList.add(value);
                        currentStr = currentStr.substring(pos);
                }
            }
        }
        String[] strArray = new String[strList.size()];
        return strList.toArray(strArray);
    }

    /**
     * Return the next char in the given string that is not a ' ' space character.
     */
    private static char getNextChar(String str){
        return getNextCharAt(str, 0);
    }

    /**
     * Return the next char in the given string that is not a ' ' space character, starting at the given position
     */
    private static char getNextCharAt(String str, int pos){
        for (int i = pos; i < str.length(); i++){
            if (str.charAt(i) != ' '){
                return str.charAt(i);
            }
        }
        return ' ';
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

    /**
     * Return the first string encountered after the field name tag
     */
    public static String getStrContentOf(String fieldName, String line) throws NoSuchFieldException {
        String catTag = getCategoryTag(fieldName);
        int posCat = getIndexOfCatTag(catTag, line);
        int posBegin = posCat + catTag.length();
        return getStrAt(line, posBegin);
    }

    /**
     * Return the first '"' encased string from the given string, starting at the given position.
     */
    private static String getStrAt(String line, int pos){
        int posBegin = line.indexOf('\"', pos) +1;
        int posEnd = line.indexOf('\"', posBegin);
        return line.substring(posBegin, posEnd);
    }

    /**
     * Extract a JSON attribute (all primitives excepted Strings) and return it as a String.
     */
    private static String getAttributeSubstringOf(String attributeName, String line) throws NoSuchFieldException {
        String catTag = getCategoryTag(attributeName);
        int posCat = getIndexOfCatTag(catTag, line);
        int posBegin = posCat + catTag.length();
        return getAttributeSubstringAt(line, posBegin);
    }

    private static String getAttributeSubstringAt(String line, int pos){
        int posBegin = line.indexOf(getNextCharAt(line, pos), pos);
        int posEnd = line.indexOf(',', posBegin +1);
        if(posEnd == -1){
            posEnd= line.indexOf(' ', posBegin +1);
        }
        if(posEnd == -1){
            posEnd= line.indexOf('}', posBegin +1);
        }
        if(posEnd == -1){
            posEnd= line.indexOf(']', posBegin +1);
        }
        return line.substring(posBegin, posEnd);
    }

    /**
     * Extract a JSON floating point number attribute from a JSON String.
     */
    public static float getFloatContentOf(String attributeName, String line) throws NoSuchFieldException {
        return Float.parseFloat(getAttributeSubstringOf(attributeName, line));
    }

    /**
     * Extract a JSON double precision floating point number attribute from a JSON String.
     */
    public static double getDoubleContentOf(String attributeName, String line) throws NoSuchFieldException {
        return Double.parseDouble(getAttributeSubstringOf(attributeName, line));
    }

    /**
     * Extract a JSON integer attribute from a JSON String.
     */
    public static int getIntContentOf(String attributeName, String line) throws NoSuchFieldException {
        return Integer.parseInt(getAttributeSubstringOf(attributeName, line));
    }

    /**
     * Extract a JSON long integer attribute from a JSON String.
     */
    public static long getLongContentOf(String attributeName, String line) throws NoSuchFieldException {
        return Long.parseLong(getAttributeSubstringOf(attributeName, line));
    }

    /**
     * Extract a JSON boolean attribute from a JSON String.
     */
    public static boolean getBoolContentOf(String attributeName, String line) throws NoSuchFieldException {
        return getAttributeSubstringOf(attributeName, line).contains("true");
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
