package com.kasteelharry.AoC.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputConverter {

    private final String path;

    /**
     * Constructor method.
     * @param path the path to the file to convert
     */
    public InputConverter(String path) {
        this.path = path;
    }

    /**
     * Convert file with numbers into a list of integers.
     * @return List with integers or null if an error occurred.
     */
    public List<Long> convertLongFile() {
        try {
            BufferedReader in = new BufferedReader(new FileReader(path));
            String str;

            List<Long> list = new ArrayList<>();
            while ((str = in.readLine()) != null) {
                list.add(Long.parseLong(str));
            }
            return list;
        } catch (IOException ie) {
            System.out.println("something went wrong");
        } catch (NumberFormatException ne) {
            System.out.println("Please sanitize your input. (non number character found.");
        }
        return null;
    }

    /**
     * Convert file with numbers into a list of integers.
     * @return List with integers or null if an error occurred.
     */
    public List<Integer> convertIntegerFile() {
        try {
            BufferedReader in = new BufferedReader(new FileReader(path));
            String str;

            List<Integer> list = new ArrayList<>();
            while ((str = in.readLine()) != null) {
                list.add(Integer.parseInt(str));
            }
            return list;
        } catch (IOException ie) {
            System.out.println("something went wrong");
        } catch (NumberFormatException ne) {
            System.out.println("Please sanitize your input. (non number character found.");
        }
        return null;
    }

    /**
     * Convert file with a String into a list of Strings.
     * @return list with Strings or null if an error occurred.
     */
    public List<String> convertStringFile() {
        try {
            BufferedReader in = new BufferedReader(new FileReader(path));
            String str;

            List<String> list = new ArrayList<>();
            while ((str = in.readLine()) != null) {
                list.add(str);
            }
            return list;
        } catch (IOException ie) {
            System.out.println("something went wrong");
        }
        return null;
    }
}
