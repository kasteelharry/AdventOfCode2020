package com.kasteelharry.AoC.Day7;

import com.kasteelharry.AoC.util.InputConverter;

import java.util.*;

public class CheckBags {

    private static final String PATH_FILE = "src/com/kasteelharry/AoC/files/inputDaySeven.txt";
    private static List<String> input = new ArrayList<>();
    private static Map<String, String> inputMap = new HashMap<>();

    /**
     * Sets the input of the challenge into the input list.
     */
    private static void setInput() {
        InputConverter iC = new InputConverter(PATH_FILE);
        input = iC.convertStringFile();
    }

    /**
     * Gets all the bags containing the bags that are passed as parameter.
     * @param oldBag All the bags to find in the input.
     * @return Set of all the bags containing the old bags + old bags.
     */
    private static Set<String> getBags(Set<String> oldBag) {
        Set<String> bags = new HashSet<>(oldBag);
        for (String line : input) {
            for (String bag : oldBag) {
                String split = line.split("contain ")[1];
                if (split.contains(bag)) {
                    bags.add(line.split("contain ")[0].replace(" bags", ""));
                    break;
                }
            }
        }

        return bags;
    }

    /**
     * Goes through the contents of the shiny gold bag and
     * keeps calling itself until all the bags in the gold bag
     * and their nested bags are added to a set.
     * @param bag the bags to find it's contents.
     * @return set of bags that are inside the shiny golden bag.
     */
    private static Set<String> recurseBags(Set<String> bag) {
        Set<String> copy = new HashSet<>(bag);
        bag.add("shiny gold");
        int length = 0;
        while (length != bag.size()) {
            length = bag.size();
            bag.addAll(getBags(bag));
        }
        if (copy.size() == bag.size()) {
            return bag;
        } else {
            return recurseBags(bag);
        }
    }

    /**
     * counts all the bags inside a bag.
     * @param bag the bag whose contents needs to be counted.
     * @return the amount of bags.
     */
    private static int countBags(String bag) {
        int amount = 1;
        String line = inputMap.get(bag);
        String[] args = line.split(", ");

        for (String a : args) {
            if (!a.contains("no other")) {
                String[] parts = a.split(" ");
                amount += Integer.parseInt(parts[0]) * countBags(parts[1] + " " + parts[2]);
            }
        }
        return amount;
    }

    /**
     * Adds all the colours to a set and returns that set.
     * @param bags the set with all the bags.
     * @return a set with only the colours of the bags.
     */
    private static Set<String> getColours(Set<String> bags) {
        Set<String> col = new HashSet<>();
        for (String bag : bags) {
            String color = bag.split("\\s")[0];
            col.add(color);
        }
        return col;
    }

    public static void main(String[] args) {
        setInput();
        System.out.println(getColours(recurseBags(new HashSet<>())).size() - 1);
        for (String s : input) {
            String[] lineHalves = s.split(" contain ");
            inputMap.put(lineHalves[0].replace(" bags", ""), lineHalves[1]);
        }
        System.out.println(countBags("shiny gold") - 1);
    }

}
