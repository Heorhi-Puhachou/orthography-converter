package io.github.heorhipuhachou.orthography.util;

public class StringUtilGet {

    public static String findFirstVowel(String word) {
        char[] chars = word.toCharArray();

        for (int i = 0; i < word.length(); i++) {
            if (StringUtilCheck.isVowel("" + chars[i])) {
                return "" + chars[i];
            }
        }
        return null;
    }

    public static int getVowelQuantity(String word) {
        char[] chars = word.toCharArray();
        int quantity = 0;

        for (int i = 0; i < word.length(); i++) {
            if (StringUtilCheck.isVowel("" + chars[i])) {
                quantity++;
            }
        }
        return quantity;
    }

    public static String getLastSymbol(String input) {
        return input.substring(input.length() - 1);
    }
}
