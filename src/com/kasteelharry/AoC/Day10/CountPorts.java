package com.kasteelharry.AoC.Day10;

import com.kasteelharry.AoC.util.InputConverter;

import java.util.*;

public class CountPorts {

    private static final String PATH_FILE = "src/com/kasteelharry/AoC/files/inputDayTen.txt";
    private static List<Integer> input = new ArrayList<>();
    private static int diffOne = 0;
    private static int diffThree = 0;


    /**
     * Converts the input file into the input list.
     */
    private static void setInput() {
        InputConverter iC = new InputConverter(PATH_FILE);
        input = iC.convertIntegerFile();
        input.add(0, 0);
        Collections.sort(input);
        input.add(input.get(input.size() - 1) + 3);

    }

    /**
     * Counts the all the difference of 1 and difference of 3.
     */
    private static void countDeltaX() {
        for (int i = 1; i < input.size(); i++) {
            int diff = input.get(i) - input.get(i - 1);
            if (diff == 1) {
                diffOne++;
            } else if (diff == 3) {
                diffThree++;
            }
        }
    }

    /**
     * Prints the total number of all the possible sequences.
     */
    private static void sequence() {
        int[] offset = new int[]{1, 1, 2, 4, 7, 15};
        long result = 1;
        int count = 0;
        for (int i = 1; i < input.size() - 1; i++) {

            int diff = input.get(i) - input.get(i - 1);
            if (diff == 1) {
                count++;
            } else if (diff == 3) {

                result *= offset[count];
                count = 0;
            }
        }
        if (count != 0) {
            result *= offset[count];
        }

        System.out.println(result);
    }

    public static void main(String[] args) {
        setInput();
        countDeltaX();
        System.out.println(diffOne * diffThree);
        sequence();
    }

}
