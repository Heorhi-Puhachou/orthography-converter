package io.github.heorhipuhachou.orthography.parser;


import io.github.heorhipuhachou.orthography.util.WordCase;

public class ParsedElement {

    private String delimiter;
    private String originalWord;
    private String word;
    private WordCase wordCase;

    public ParsedElement(String delimiter, String originalWord) {
        this.delimiter = delimiter;
        this.originalWord = originalWord;
        this.word = originalWord.toLowerCase();
        this.wordCase = getWordCase(originalWord);
    }

    public String getDelimiter() {
        return delimiter;
    }

    public String getOriginalWord() {
        return originalWord;
    }

    public String getWord() {
        return word;
    }

    public WordCase getWordCase() {
        return wordCase;
    }

    @Override
    public String toString() {
        return "ParsedElement{" +
                "delimiter='" + delimiter + '\'' +
                ", originalWord='" + originalWord + '\'' +
                ", word='" + word + '\'' +
                ", upperCase=" + wordCase +
                '}';
    }

    private static WordCase getWordCase(String word) {
        if (word == null || word.isEmpty()) {
            return WordCase.OTHER;
        }

        if (word.equals(firstLetterToUpperCase(word))) {
            return WordCase.FIRST_LETTER_UPPER;
        }

        if (word.equals(word.toUpperCase())) {
            return WordCase.ALL_LETTERS_UPPER;
        }

        if (word.equals(word.toLowerCase())) {
            return WordCase.ALL_LETTERS_LOWER;
        }

        return WordCase.OTHER;
    }

    private static String firstLetterToUpperCase(String word) {
        String low = word.toLowerCase();
        String first = low.substring(0, 1);
        return first.toUpperCase() + low.substring(1);
    }
}
