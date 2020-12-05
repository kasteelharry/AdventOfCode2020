package com.kasteelharry.AoC.Day5;

import com.kasteelharry.AoC.util.InputConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class CheckSeats {

    private static final String PATH_FILE = "src/com/kasteelharry/AoC/files/inputDayFive.txt";

    private static final int ROWS = 127;
    private static final int COL = 7;
    private static List<String> input;


    /**
     * Calculate the row of the boarding pass.
     * @param boardPass the boarding pass to check
     * @return the row of the boarding pass.
     */
    protected static int calculateRow(String boardPass) {
        int[] row = IntStream.rangeClosed(0, ROWS).toArray();
        int stringLength = boardPass.length() - 3;
        row = calcHalves(row, stringLength, boardPass);

        return row[0];
    }

    /**
     * Calculates the column of the boarding pass.
     * @param boardPass the boarding pass to check.
     * @return the column of the boarding pass.
     */
    protected static int calculateCol(String boardPass) {
        int[] row = IntStream.rangeClosed(0, COL).toArray();
        int stringLength = 3;
        row = calcHalves(row, stringLength, boardPass.substring(boardPass.length() - 3));

        return row[0];
    }

    /**
     * This method returns the half of the input array based on the string length.
     * @param arr the array to halve.
     * @param stringLength the amount of times it has to halve the array.
     * @param boardPass the string to decide which half to take.
     * @return an array with the remaining integers.
     */
    private static int[] calcHalves(int[] arr, int stringLength, String boardPass) {
        int[] arrClone = arr.clone();
        for (int i = 0; i < stringLength; i++) {
            int[] upper = Arrays.copyOfRange(arrClone, arrClone.length / 2, arrClone.length);
            int[] lower = Arrays.copyOfRange(arrClone, 0, arrClone.length / 2);
            char temp = boardPass.charAt(i);
            if (temp == 'F' || temp == 'L') {
                arrClone = lower;
            } else {
                arrClone = upper;
            }

        }
        return arrClone;
    }

    /**
     * Calculates the seat id.
     * @param row the row of the seat
     * @param col the column of the seat.
     * @return the seat id.
     */
    private static int calculateSeatID(int row, int col) {
        return row * 8 + col;
    }

    /**
     * Creates a list with all the possible seat ids.
     * @return all the seat ids.
     */
    private static List<Integer> getAllSeatID() {
        List<Integer> seatID = new ArrayList<>();
        for (String pass: input) {
            int row = calculateRow(pass);
            int col = calculateCol(pass);
            int id = calculateSeatID(row, col);
            seatID.add(id);
        }
        Collections.sort(seatID);
        return seatID;
    }

    /**
     * Gets the maximum seat ID from the list.
     * @param seatID a (unsorted) list with all the seat ids.
     * @return the maximum seat ID in the list.
     */
    private static int getMaxSeatID(List<Integer> seatID) {
        return Collections.max(seatID);
    }

    /**
     * Finds all the seat IDs that have been missing from the seat ID list.
     * Will stop at the highest available seat ID.
     * @param seatID a list with all already found seat IDs.
     * @return a list with all the seat IDs that have been missing.
     */
    private static List<Integer> findMissingID(List<Integer> seatID) {
        List<Integer> missingList = new ArrayList<>();
        int index = 0;
        for (int i = 0; i < getMaxSeatID(seatID); i++) {
            if (seatID.get(index) == i) {
                index++;
            } else {
                missingList.add(i);
            }
        }

        return missingList;
    }

    /**
     * Finds the seat ID that does not have another empty seat directly next to it.
     * @param missingSeats a (unsorted) list with all the missing seat IDs.
     * @return the seat ID of the seat that has no empty seats next to it.
     */
    private static int findSeatID(List<Integer> missingSeats) {
        int id = 0;
        Collections.sort(missingSeats);
        for (int i = 1; i < missingSeats.size(); i++) {
            if (!(missingSeats.get(i) - missingSeats.get(i - 1) == 1)) {
                id = missingSeats.get(i);
            }
        }

        return id;
    }

    public static void main(String[] args) {
        InputConverter iC = new InputConverter(PATH_FILE);
        input = iC.convertStringFile();
        System.out.println(getMaxSeatID(getAllSeatID()));
        System.out.println(findSeatID(findMissingID(getAllSeatID())));
    }

}
