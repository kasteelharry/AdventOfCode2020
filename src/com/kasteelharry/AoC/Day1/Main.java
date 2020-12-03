package com.kasteelharry.AoC.Day1;


import com.kasteelharry.AoC.util.InputConverter;

import java.util.List;

public class Main {
    private static final String PATH_FILE = "src/com/kasteelharry/AoC/files/inputDayOne.txt";

    public static void main(String[] args) {
        InputConverter iC = new InputConverter(PATH_FILE);

        List<Integer> input = iC.convertIntegerFile();

        // Three nested for loops indicate how much effort I put into today
        // Which wasn't a lot.
        for (int fst: input) {
            for (int snd : input) {
                if (fst + snd == 2020) {
                    System.out.println(fst * snd);
                }
                for (int trd : input) {
                    if (fst + snd + trd == 2020) {
                        System.out.println("Second entry:");
                        System.out.println(fst * snd * trd);
                    }
                }
            }
        }
    }
}
