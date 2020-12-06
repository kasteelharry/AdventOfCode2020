package com.kasteelharry.AoC.Day6;

import com.kasteelharry.AoC.util.InputConverter;

import java.util.*;

public class CheckQuestions {

    private static final String PATH_FILE = "src/com/kasteelharry/AoC/files/inputDaySix.txt";
    private static List<String> input;


    /**
     * Creates a list with sets of each character in the group;
     * only duplicates are kept if the flag = 0.
     * @param flag 0 to store all the charactes; 1 to only keep duplicates.
     * @return a list of character sets.
     */
    private static List<Set<Character>> convertInput(int flag) {
        List<Set<Character>> result = new ArrayList<>();

        List<Character> group = new ArrayList<>();
        List<Character> previous = new ArrayList<>();

        for (String entry : input) {

            if (!(entry.isBlank() || entry.isEmpty())) {


                if (flag == 0) {
                    group = new ArrayList<>(stringToCharSet(new TreeSet<>(group), entry));
                } else if (flag == 1) {
                    if (previous.size() == 0) {
                        group = new ArrayList<>(stringToCharSet(new TreeSet<>(group), entry));
                        previous = new ArrayList<>(group);
                    }
                    if (group.size() > 0) {
                        group.retainAll(stringToCharSet(new TreeSet<>(), entry));
                    }
                }


            } else {
                result.add(new TreeSet<>(group));
                previous.clear();
                group.clear();


            }
        }
        result.add(new TreeSet<>(group));

        return result;
    }

    /**
     * Converts a string into characters and adds it to an existing set of characters.
     * @param set the set to manipulate.
     * @param inputString the string to change into characters.
     * @return the set with all the characters added to it.
     */
    private static Set<Character> stringToCharSet(Set<Character> set, String inputString) {

        for (char c : inputString.toCharArray()) {
            set.add(c);
        }
        return set;
    }

    /**
     * Adds the length of all the sets to one another.
     * @param cList list with sets.
     * @return all the set lengths added to one another.
     */
    private static int countSetsLength(List<Set<Character>> cList) {
        int count = 0;
        for (Set<Character> s : cList) {
            count += s.size();
        }

        return count;
    }

    /**
     * Test function.
     * @param list list of strings to test.
     * @return count.
     */
    public static int runTest(List<String> list) {
        input = list;
        return countSetsLength(convertInput(1));
    }


    public static void main(String[] args) {
        InputConverter iC = new InputConverter(PATH_FILE);
        input = iC.convertStringFile();
        System.out.println(countSetsLength(convertInput(0)));
        System.out.println(countSetsLength(convertInput(1)));
    }

}
