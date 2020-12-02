package com.kasteelharry.AoC.DayTwo;

import com.kasteelharry.AoC.util.InputConverter;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class checkPasswords {
    private static final String pathFile = "src/com/kasteelharry/AoC/files/inputDayTwo.txt";

    /**
     * Converts the input string into an array.
     * @param line the string accuired from the file.
     * @return array with min, max, letter, password.
     */
    public static String[] convertInput(String line) {
        String[] input = new String[4];

        String[] split1 = line.split(":");
        String[] split2 = split1[0].split("-");
        String[] split3 = split2[1].split("\\s");

        input[0] = split2[0]; //minimum number
        input[1] = split3[0]; //maximum number
        input[2] = split3[1]; //letter that needs to occur
        input[3] = split1[1].trim(); //the password

        return input;
    }

    /**
     * Checks if the letter is in the password as required by the assignment
     * @param inputString array generated from convertInput()
     * @return true if the password is valid.
     */
    public static boolean checkValidity(String[] inputString) {
        long min;
        long max;
        try {
            min = Long.parseLong(inputString[0]);
            max = Long.parseLong(inputString[1]);
            // Regex find all occurrences of the letter.
            Pattern pattern = Pattern.compile("[" + inputString[2] + "]");
            Matcher matcher = pattern.matcher(inputString[3]);
            long matches = matcher.results().count();

            return matches <= max && matches >= min;

        } catch (NumberFormatException nm) {
            System.out.println("Tried to parse an non castable character to long.");
            nm.printStackTrace();
        }
        return false;
    }

    /**
     * Checks if the password is valid according to the tougher demands
     * @param inputString array generated from convertInput()
     * @return true if the password is valid.
     */
    public static boolean checkComplicatedValidity(String[] inputString) {
        int first;
        int second;
        try {
            first = Integer.parseInt(inputString[0]);
            second = Integer.parseInt(inputString[1]);
            char letter = inputString[2].charAt(0);

            if (letter == inputString[3].charAt(first - 1) ||
                    letter == inputString[3].charAt(second - 1)) {
                if (letter != inputString[3].charAt(first - 1) ||
                        letter != inputString[3].charAt(second - 1)) {
                    return true;
                }
            }



        } catch (NumberFormatException nm) {
            System.out.println("Tried to parse an non castable character to int.");
            nm.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {

        InputConverter iC = new InputConverter(pathFile);
        List<String> inputList = iC.convertStringFile();
        int count = 0;
        for (String input : inputList) {
            if (checkComplicatedValidity(convertInput(input))) {
                count++;
            }
        }
        System.out.println(count);

    }
}
