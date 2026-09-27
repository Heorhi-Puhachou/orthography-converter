package io.github.heorhipuhachou.orthography.util;

public class StringUtilTransform {

    public static String firstLetterToUpperCase(String word) {
        String low = word.toLowerCase();
        String first = low.substring(0, 1);
        return first.toUpperCase() + low.substring(1);
    }

    public static String transformCase(WordCase wordCase, String word) {
        switch (wordCase) {
            case FIRST_LETTER_UPPER:
                return firstLetterToUpperCase(word);
            case ALL_LETTERS_UPPER:
                return word.toUpperCase();
            case ALL_LETTERS_LOWER:
                return word.toLowerCase();
            default:
                return word;
        }
    }
}
