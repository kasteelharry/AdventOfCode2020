package com.kasteelharry.AoC.DayOne;


import com.kasteelharry.AoC.util.InputConverter;

import java.util.List;

public class main {
    private static final String pathFile = "src/com/kasteelharry/AoC/files/inputDayOne.txt";

    public static void main(String[] args) {
        InputConverter iC = new InputConverter(pathFile);

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
