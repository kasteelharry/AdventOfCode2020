package com.kasteelharry.AoC.Day8;

import com.kasteelharry.AoC.util.InputConverter;

import java.util.*;

public class BootInstructions {

    private static final String PATH_FILE = "src/com/kasteelharry/AoC/files/inputDayEight.txt";
    private static List<String> input = new ArrayList<>();
    private static int accumulator = 0;

    /**
     * Converts the input file into the input list.
     */
    private static void setInput() {
        InputConverter iC = new InputConverter(PATH_FILE);
        input = iC.convertStringFile();
    }

    /**
     * Iterates over the input following the rules of the operations.
     * @return 0 if it ended naturally; -1 if it ends in an infinite loop.
     */
    private static int iterateInput() {
        Map<Integer, String> map = new HashMap<>();
        int instruction = 1;
        int index = 0;

        List<Integer> pastIndices = new ArrayList<>();
        while (index < input.size()) {
            String[] l = input.get(index).split(" +");
            String op = l[0];
            int acc = Integer.parseInt(l[1]);
            boolean test = map.containsValue(Arrays.toString(l));

            if (test && checkIndex(pastIndices, index)) {
                System.out.println(Arrays.toString(l));
                System.out.println(accumulator);
                return -1;
            } else {
                map.put(instruction, Arrays.toString(l));
                pastIndices.add(index);
            }

            instruction++;

            switch (op) {
                case "nop":
                    index++;
                    break;
                case "acc":

                    accumulator += acc;
                    index++;
                    break;
                case "jmp":
                    index += acc;
                    break;
                default:
                    System.out.println("Error in switch case");
                    break;
            }
        }
        return 0;
    }

    /**
     * Iterates over the input and flips the 'jmp' and 'nop' instructions
     * one at the time and checks if the file doesn't enter an infinite loop.
     */
    private static void flipThem() {
        int flippedIndex = -1;
        List<String> inputCopy = new ArrayList<>(input);
        for (String ignored : input) {

            accumulator = 0;
            flippedIndex++;

            String[] sl = ignored.split(" +");
            String op = sl[0];

            if (op.equals("jmp") || op.equals("nop")) {
                if (op.equals("jmp")) {
                    sl[0] = "nop";
                } else {
                    sl[0] = "jmp";
                }

                input.set(flippedIndex, sl[0] + " " + sl[1]);

                if (iterateInput() == 0) {

                    System.out.println(accumulator);
                    break;
                } else {
                    input.set(flippedIndex, inputCopy.get(flippedIndex));
                }
            }

        }

    }

    /**
     * Checks if the integer is in the list of integers.
     * @param pastIndices list of integers.
     * @param index the integer to find.
     * @return true if in list; false if not in list.
     */
    private static boolean checkIndex(List<Integer> pastIndices, int index) {
        for (int i : pastIndices) {
            if (i == index) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        setInput();
        iterateInput();
        flipThem();
    }
}
