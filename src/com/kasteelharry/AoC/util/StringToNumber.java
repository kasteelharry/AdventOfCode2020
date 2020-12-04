package com.kasteelharry.AoC.util;

public class StringToNumber {

    /**
     * Converts string to an integer.
     * @param string the string to parse.
     * @return int with the value in the string.
     */
    public static int stringToInt(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException ne) {
            System.out.println("Wrong input given.");
            ne.printStackTrace();
            return 0;
        }
    }

    /**
     * Converts string to an Long.
     * @param string the string to parse.
     * @return long with the value in the string.
     */
    public static long stringToLong(String string) {
        try {
            return Long.parseLong(string);
        } catch (NumberFormatException ne) {
            System.out.println("Wrong input given.");
            ne.printStackTrace();
            return 0;
        }
    }
}
