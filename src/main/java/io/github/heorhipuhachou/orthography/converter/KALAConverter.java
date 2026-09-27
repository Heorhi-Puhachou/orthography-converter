package io.github.heorhipuhachou.orthography.converter;

import io.github.heorhipuhachou.orthography.Converter;
import io.github.heorhipuhachou.orthography.parser.ParsedElement;
import io.github.heorhipuhachou.orthography.parser.Parser;
import io.github.heorhipuhachou.orthography.util.StringUtilCheck;
import io.github.heorhipuhachou.orthography.util.StringUtilGet;

import java.util.ArrayList;
import java.util.HashMap;

import static io.github.heorhipuhachou.orthography.util.StringUtilTransform.transformCase;

public class KALAConverter implements Converter {

    private final Parser parser;
    private final HashMap<String, String> pairs;
    private final HashMap<String, String> softLetters;

    public KALAConverter() {

        this.parser = new Parser();
        pairs = new HashMap<>();
        pairs.put("а", "a");
        pairs.put("б", "b");
        pairs.put("в", "v");
        pairs.put("г", "h");
        pairs.put("д", "d");
        pairs.put("е", "je");
        pairs.put("ё", "jo");
        pairs.put("ж", "ž");
        pairs.put("з", "z");
        pairs.put("і", "i");
        pairs.put("й", "j");
        pairs.put("к", "k");
        pairs.put("л", "l");
        pairs.put("м", "m");
        pairs.put("н", "n");
        pairs.put("о", "o");
        pairs.put("п", "p");
        pairs.put("р", "r");
        pairs.put("с", "s");
        pairs.put("т", "t");
        pairs.put("у", "u");
        pairs.put("ў", "ŭ");
        pairs.put("ф", "f");
        pairs.put("х", "ch");
        pairs.put("ц", "c");
        pairs.put("ч", "č");
        pairs.put("ш", "š");
        pairs.put("ы", "y");
        pairs.put("ь", "");
        pairs.put("э", "e");
        pairs.put("ю", "ju");
        pairs.put("я", "ja");

        softLetters = new HashMap<>();
        softLetters.put("l", "ĺ");
        softLetters.put("n", "ń");
        softLetters.put("c", "ć");
        softLetters.put("s", "ś");
        softLetters.put("z", "ź");
    }

    @Override
    public String convert(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        ArrayList<ParsedElement> elements = parser.parse(text);
        StringBuilder result = new StringBuilder();

        for (ParsedElement current : elements) {
            if (StringUtilCheck.isEngWord(current.getOriginalWord())) {
                result.append(current.getDelimiter()).append(current.getOriginalWord());
            } else {
                result.append(current.getDelimiter()).append(convertElement(current));
            }
        }

        return result.toString();
    }

    private String convertElement(ParsedElement current) {
        String convertedValue = advancedReplace(current.getWord());
        convertedValue = transformCase(current.getWordCase(), convertedValue);
        return convertedValue;
    }

    private String advancedReplace(String word) {
        String result = "";

        char[] chars = word.toCharArray();

        for (int i = 0; i < word.length(); i++) {
            if (StringUtilCheck.isApostrophe(chars[i])) {
                // apostraf prapuskajecca
            } else if (StringUtilCheck.isNumber(chars[i]) || StringUtilCheck.isEngSymbol(chars[i])) {
                result = result + chars[i];
            } else {
                if (i > 0 && (chars[i] == 'ь')) {
                    result = replaceLastWithSoft(result);
                }

                String symbol = pairs.get("" + chars[i]);
                if (i > 0 && StringUtilCheck.isSoftVowel(chars[i])
                        && !StringUtilCheck.isVowel(chars[i - 1])
                        && chars[i - 1] != 'ь'
                        && chars[i - 1] != 'ў'
                        && !StringUtilCheck.isApostrophe(chars[i - 1])) {
                    symbol = symbol.replace("j", "i");
                }
                result = result + symbol;
            }
        }

        return result;
    }


    private String replaceLastWithSoft(String word) {
        if (word.length() == 1) {
            return softLetters.get(word);
        }
        String replacement = softLetters.get(StringUtilGet.getLastSymbol(word)) == null ? StringUtilGet.getLastSymbol(word) : softLetters.get(StringUtilGet.getLastSymbol(word));
        return word.substring(0, word.length() - 1) + replacement;
    }
}
