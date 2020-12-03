package com.kasteelharry.AoC.Day3;

import com.kasteelharry.AoC.util.InputConverter;

import java.util.List;

public class CountTrees {

    private static final String PATHFILE = "src/com/kasteelharry/AoC/files/inputDayThree.txt";
    private static int shift = 3;
    private static int down = 1;
    private static final int LINESIZE = 30;
    private static final char TREE = '#';

    private static final int[] SLOPES = new int[] {1, 3, 5, 7, 1};
    private static final int[] DOWN_SLOPES = new int[] {1, 1, 1, 1, 2};


    /**
     * This method checks if the char at the pos is a tree or not.
     * @param line the slope to check.
     * @param pos the position to check
     * @return true if the position is a tree; false if not.
     */
    private static boolean checkTree(String line, int pos) {
        return line.charAt(pos) == TREE;
    }

    /**
     * Calculates the next position of the tree to check according to the shift integer.
     * @param oldPos the previous position that was checked.
     * @return the new position to be checked.
     */
    private static int calcPos(int oldPos) {
        int newPos = oldPos + shift;
        if (newPos > LINESIZE) {
            newPos = newPos - LINESIZE - 1;
        }
        return newPos;
    }

    /**
     * Calculates the next line number to be checked according to the down integer.
     * @param oldLine the previous line that was checked.
     * @return the new line number that has to be checked.
     */
    private static int calcLine(int oldLine) {
        return oldLine + down;
    }

    /**
     * calculates all the threes in an input list.
     * @param input the list containing all the strings.
     * @return the number of threes in that list.
     */
    public static int countAllTrees(List<String> input) {
        int pos = 0;
        int line = 0;
        int count = 0;
        for (int i = line; i < input.size(); i = calcLine(i)) {
            if (checkTree(input.get(i), pos)) {
                count++;
            }
            pos = calcPos(pos);

        }
        return count;
    }



    public static void main(String[] args) {
        // Convert file to list.
        InputConverter iC = new InputConverter(PATHFILE);
        final List<String> inputList = iC.convertStringFile();

        long finalCount = 1;
        // for every slope calculate the trees and multiply them.
        for (int i = 0; i < SLOPES.length; i++) {
            shift = SLOPES[i];
            down = DOWN_SLOPES[i];
            int temp = countAllTrees(inputList);
            finalCount = finalCount * temp;
            System.out.println("Results of right " + shift + " down " +
                    down + " is: " + temp);
        }

        System.out.println("The final count is: " + finalCount + " trees");
    }


}
