package com.kasteelharry.AoC.Day4;

import com.kasteelharry.AoC.util.InputConverter;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.kasteelharry.AoC.util.StringToNumber.*;

public class CheckPassports {

    private static final String PATH_FILE = "src/com/kasteelharry/AoC/files/inputDayFour.txt";

    private static final String[] FIELDS = new String[]
        {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};


    /**
     * Combines the input into a nested list for each entry.
     * @param input the list containing all the lines retrieved from the file.
     * @return a nested list where each nested list is one entry.
     */
    public static List<List<String>> getEntries(List<String> input) {
        List<List<String>> convertedInput = new ArrayList<>();
        List<String> subString = new ArrayList<>();

        for (String entry : input) {

            if (!(entry.isBlank() || entry.isEmpty())) {
                subString.addAll(Arrays.asList(entry.split("\\s")));
            } else {
                convertedInput.add(new ArrayList<>(subString));
                subString.clear();
            }
        }
        convertedInput.add(new ArrayList<>(subString));
        return convertedInput;
    }

    /**
     * Converts the nested list into a map where the attribute type is the key
     * and the attribute's value is the map value.
     * @param input a nested list where each nested list is an entry of a passport.
     * @return a nested map in a list with the attribute name and value.
     */
    public static List<Map<String, String>> mapEntries(List<List<String>> input) {
        List<Map<String, String>> resultList = new ArrayList<>();


        for (List<String> s : input) {
            Map<String, String> passportEntry = new HashMap<>();
            for (String attribute: s) {
                String[] split = attribute.split(":");
                passportEntry.put(split[0], split[1]);
            }
            resultList.add(passportEntry);
        }

        return resultList;
    }

    /**
     * Checks if the passport entry contains all the necessary fields.
     * @param passport list of each password's attributes.
     * @return a string array with all the attributes present in the list.
     */
    public static String[] checkFields(List<String> passport) {
        String[] contains = new String[FIELDS.length];

        for (int i = 0; i < FIELDS.length; i++) {
            Pattern pattern = Pattern.compile(FIELDS[i] + ":");
            for (String line : passport) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.results().count() == 1) {
                    contains[i] = FIELDS[i];
                    break;
                }
            }
        }
        return contains;
    }

    /**
     * Checks the birth year.
     * @param year the year to check.
     * @return true if within the hardcoded range.
     */
    public static boolean checkBirthYear(String year) {
        int intYear = stringToInt(year);
        return intYear >= 1920 && intYear <= 2002;
    }

    /**
     * Checks the issue year.
     * @param year the year to check.
     * @return true if within the hardcoded range.
     */
    public static boolean checkIssueYear(String year) {
        int intYear = stringToInt(year);
        return intYear >= 2010 && intYear <= 2020;
    }

    /**
     * Checks the expire year.
     * @param year the year to check.
     * @return true if within the hardcoded range.
     */
    public static boolean checkExpireYear(String year) {
        int intYear = stringToInt(year);
        return intYear >= 2020 && intYear <= 2030;
    }

    /**
     * Checks the height is within the given range.
     * @param height a string that contains xxxcm or xxin;
     *               where xxx are the height in either centimeters or inches.
     * @return true if within the hardcoded range.
     */
    public static boolean checkHeight(String height) {
        String substring = height.substring(0, height.length() - 2);
        if (height.charAt(height.length() - 1) == 'm') {
            int heightNum = stringToInt(substring);
            return heightNum >= 150 && heightNum <= 193;
        } else if (height.charAt(height.length() - 1) == 'n') {
            int heightNum = stringToInt(substring);
            return heightNum >= 59 && heightNum <= 76;
        }
        return false;
    }

    /**
     * Checks if the hair colour is in proper format (#1234AF).
     * @param hairColour string with the hair colour.
     * @return true if the format is correct.
     */
    public static boolean checkHairColour(String hairColour) {
        if (hairColour.charAt(0) == '#') {
            String sub = hairColour.substring(1);
            for (int i = 0; i < sub.length(); i++) {
                char c = sub.charAt(i);
                if (Character.isDigit(c) || (c >= 'a' && c <= 'f')) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if the eye colour is in the given list.
     * @param eyeColour string with the eye colour value.
     * @return true if the parameter is within the hard coded list.
     */
    public static boolean checkEyeColour(String eyeColour) {
        String[] colours = new String[] {"amb", "blu", "brn", "gry", "grn", "hzl", "oth"};
        for (String col : colours) {
            if (eyeColour.equals(col)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the passport has a valid ID.
     * @param passportID string with the passport id.
     * @return true if it is a valid passport id.
     */
    public static boolean checkPassportID(String passportID) {
        for (int i = 0; i < passportID.length(); i++) {
            if (!Character.isDigit(passportID.charAt(i))) {
                return false;
            }
        }
        return stringToLong(passportID) > 0 && passportID.length() == 9;
    }


    /**
     * Checks all the attributes of a passport.
     * @param attributeMap a HashMap with all the passport attributes.
     * @return true if the passport is valid.
     */
    public static boolean checkFieldValues(Map<String, String> attributeMap) {
        for (String key : attributeMap.keySet()) {
            switch (key) {
                case "byr":
                    if (!checkBirthYear(attributeMap.get(key))) {
                        return false;
                    }
                    break;
                case "iyr":
                    if (!checkIssueYear(attributeMap.get(key))) {
                        return false;
                    }
                    break;
                case "eyr":
                    if (!checkExpireYear(attributeMap.get(key))) {
                        return false;
                    }
                    break;
                case "hgt":
                    if (!checkHeight(attributeMap.get(key))) {
                        return false;
                    }
                    break;
                case "hcl":
                    if (!checkHairColour(attributeMap.get(key))) {
                        return false;
                    }
                    break;
                case "ecl":
                    if (!checkEyeColour(attributeMap.get(key))) {
                        return false;
                    }
                    break;
                case "pid":
                    if (!checkPassportID(attributeMap.get(key))) {
                        return false;
                    }
                    break;
                default:
                    break;
            }
        }

        return true;
    }


    /**
     * Checks if a passport has all the mandatory fields.
     * @param passport list of all the attributes.
     * @return true if it has all the mandatory fields.
     */
    public static boolean checkValidity(List<String> passport) {
        String[] results = checkFields(passport);
        int emptyCount = 0;
        boolean containsCid = false;

        for (String field : results) {
            if (field == null && emptyCount <= 1) {
                emptyCount++;

            } else if (field == null) {
                return false;
            } else if (field.contains("cid")) {
                containsCid = true;
            }
        }

        return emptyCount == 0 || containsCid;

    }

    /**
     * Assignment 4.1, counts all the passwords that have the mandatory field.
     * @param inputList the entire input file as list format.
     * @return count of all the valid passports.
     */
    public static int countValidPassports(List<String> inputList) {
        int count = 0;
        List<List<String>> entries = getEntries(inputList);

        for (List<String> entry : entries) {
            if (checkValidity(entry)) {

                count++;
            }
        }

        return count;
    }

    /**
     * Counts all the valid passports that have all the fields
     * and all the requirements.
     * @param input the entire input file as list format.
     * @return count of all the valid passports.
     */
    public static int countValidPassportEntries(List<String> input) {
        List<List<String>> entries = getEntries(input);
        entries.removeIf(entry -> !checkValidity(entry));
        List<Map<String, String>> passMap = mapEntries(entries);
        int count = 0;
        for (Map<String, String> m : passMap) {
            if (checkFieldValues(m)) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        InputConverter iC = new InputConverter(PATH_FILE);
        List<String> inputList = iC.convertStringFile();
        System.out.println(countValidPassports(inputList));
        System.out.println(countValidPassportEntries(inputList));

    }
}
