package com.kasteelharry.AoC.Day9;

import com.kasteelharry.AoC.util.InputConverter;

import java.util.*;

public class DecryptPort {

    // Oh boy there are a lot of globals in here.
    // But do I care about it? No, I do not so just deal with it.
    private static final String PATH_FILE = "src/com/kasteelharry/AoC/files/inputDayNine.txt";
    private static List<Long> input = new ArrayList<>();
    private static final int PREAMP_LENGTH = 25;
    private static int baseInt = 0;
    private static long[] array = new long[PREAMP_LENGTH];
    private static List<Long> sums = new ArrayList<>();
    private static long invalidLong = 0;

    /**
     * Converts the input file into the input list.
     */
    private static void setInput() {
        InputConverter iC = new InputConverter(PATH_FILE);
        input = iC.convertLongFile();
    }

    /**
     * Initializes the main array to start with.
     */
    private static void setArray() {
        for (int i = baseInt; i < array.length; i++) {
            array[i] = input.get(i);
            baseInt++;
        }
        System.out.println(Arrays.toString(array));
    }

    /**
     * Shifts the array one index to the left.
     */
    private static void shiftArray() {
        System.arraycopy(array, 1, array, 0, array.length - 1);


        System.out.println(Arrays.toString(array));
    }

    /**
     * Calculates all the possible sums from the long values in the array.
     */
    private static void calcSums() {
        List<Long> newSums = new ArrayList<>();
        for (long i : array) {
            for (long j : array) {
                if (i != j) {
                    newSums.add(i + j);
                }
            }
        }
        sums = new ArrayList<>(newSums);
    }

    /**
     * Checks the next value in the array and if it is in the list
     * with sums. If not found stores the invalid value and prints it to the console.
     * @return false if the next value is in the sum list;
     * true if not.
     */
    private static boolean checkNext() {
        if (containsList(input.get(baseInt)))  {
            shiftArray();
            array[PREAMP_LENGTH - 1] = input.get(baseInt);
            calcSums();
            baseInt++;
            return false;
        } else {
            invalidLong = input.get(baseInt);
            System.out.println(invalidLong);
            return true;
        }
    }

    /**
     * gets the min and max value and returns the sum of them.
     * @param set the continuous set that sum up to the invalid value.
     * @return the sum of the min and max value.
     */
    private static long getMaxMin(List<Long> set) {
        Collections.sort(set);
        long min = set.get(0);
        long max = set.get(set.size() - 1);
        return min + max;
    }

    /**
     * Finds the continuous set that is equal to the invalid value.
     * @return the list with all the values that sum up to the invalid value.
     */
    private static List<Long> findSet() {
        List<Long> contSet = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            contSet = new ArrayList<>();
            contSet.add(input.get(i));
            long currentSum = input.get(i);
            for (int j = i + 1; j < input.size(); j++) {
                contSet.add(input.get(j));
                currentSum += input.get(j);
                if (currentSum > invalidLong) {
                    break;
                } else if (currentSum == invalidLong) {
                    return contSet;
                }
            }
        }
        return contSet;
    }


    /**
     * Executes the main loop for the first solution.
     * It iterates over the list and finds the invalid value.
     */
    private static void mainLoop() {
        Iterator<Long> it = input.iterator();
        while (it.hasNext()) {
            if (checkNext()) {
                break;
            }
        }
    }


    /**
     * Checks if the passed parameter is in the list of sums.
     * @param i the long value to find in the list of sums.
     * @return true if the parameter is in the list of sums;
     * false if the parameter is not in the list.
     */
    private static boolean containsList(long i) {
        for (long j : sums) {
            if (i == j) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        setInput();
        setArray();
        calcSums();
        mainLoop();
        System.out.println(findSet());
        System.out.println(getMaxMin(findSet()));
    }
}
