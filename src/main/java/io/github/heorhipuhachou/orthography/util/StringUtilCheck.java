package io.github.heorhipuhachou.orthography.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.github.heorhipuhachou.orthography.util.StringUtilGet.findFirstVowel;
import static io.github.heorhipuhachou.orthography.util.StringUtilGet.getVowelQuantity;

public class StringUtilCheck {

    public static boolean isWordSymbol(char symbol) {
        String nonDelimiterPattern = "[\\p{L}\\d']";
        Pattern pattern = Pattern.compile(nonDelimiterPattern);
        Matcher matcher = pattern.matcher("" + symbol);
        return matcher.matches();
    }

    public static boolean isVowel(String symbol) {
        String nonDelimiterPattern = "[аяоёэеуюыі]";
        Pattern pattern = Pattern.compile(nonDelimiterPattern);
        Matcher matcher = pattern.matcher(symbol);
        return matcher.matches();
    }

    public static boolean isVowel(char symbol) {
        return isVowel("" + symbol);
    }

    public static boolean isEngWord(String word) {
        String nonDelimiterPattern = "[a-zA-Z]+";
        Pattern pattern = Pattern.compile(nonDelimiterPattern);
        Matcher matcher = pattern.matcher(word);
        return matcher.matches();
    }

    public static boolean isCyrillicWord(String word) {
        String nonDelimiterPattern = "[а-яА-Я]+";
        Pattern pattern = Pattern.compile(nonDelimiterPattern);
        Matcher matcher = pattern.matcher(word);
        return matcher.matches();
    }

    public static boolean isEngSymbol(char symbol) {
        return isEngWord("" + symbol);
    }

    public static boolean isCyrillicSymbol(char symbol) {
        return Character.UnicodeBlock.of(symbol).equals(Character.UnicodeBlock.CYRILLIC);
    }

    public static boolean isNumber(String symbol) {
        String nonDelimiterPattern = "[\\d]+";
        Pattern pattern = Pattern.compile(nonDelimiterPattern);
        Matcher matcher = pattern.matcher(symbol);
        return matcher.matches();
    }

    public static boolean isNumber(char symbol) {
        return isNumber("" + symbol);
    }

    public static boolean isApostrophe(String symbol) {
        return "'".equals(symbol);
    }

    public static boolean isApostrophe(char symbol) {
        return isApostrophe("" + symbol);
    }

    public static boolean isSoftVowel(String symbol) {
        String softVowelPattern = "[яёеюі]";
        Pattern pattern = Pattern.compile(softVowelPattern);
        Matcher matcher = pattern.matcher(symbol);
        return matcher.matches();
    }

    public static boolean isSoftVowel(char symbol) {
        return isSoftVowel("" + symbol);
    }

    public static boolean isOneVowelInWord(String word) {
        return getVowelQuantity(word) == 1;
    }

    public static boolean isFirstSyllableStressed(String word) {
        return word.equals("бачу")
                || word.equals("назвы")
                || word.equals("будзе")
                || word.equals("буду")
                || word.equals("трэба")
                || word.equals("ведаю")
                || (isNumber(word) && (word.startsWith("2") || word.startsWith("3")))
                || "о".equals(findFirstVowel(word))
                || isOneVowelInWord(word);
    }
}
