package io.github.heorhipuhachou.orthography.converter;

import io.github.heorhipuhachou.orthography.Converter;
import io.github.heorhipuhachou.orthography.parser.ParsedElement;
import io.github.heorhipuhachou.orthography.parser.Parser;
import io.github.heorhipuhachou.orthography.util.StringUtilCheck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.github.heorhipuhachou.orthography.util.StringUtilTransform.transformCase;

public class LAKAConverter implements Converter {
    private final Parser parser;
    private final HashMap<String, String> pairs;
    private final HashMap<String, String> pairsL;

    public LAKAConverter() {

        this.parser = new Parser();
        pairs = new HashMap<>();
        pairs.put("a", "а");
        pairs.put("b", "б");
        pairs.put("v", "в");
        pairs.put("h", "г");
        pairs.put("d", "д");
        pairs.put("je", "е");
        pairs.put("jo", "ё");
        pairs.put("ž", "ж");
        pairs.put("z", "з");
        pairs.put("i", "і");
        pairs.put("j", "й");
        pairs.put("k", "к");
        pairs.put("l", "л");
        pairs.put("m", "м");
        pairs.put("n", "н");
        pairs.put("o", "о");
        pairs.put("p", "п");
        pairs.put("r", "р");
        pairs.put("s", "с");
        pairs.put("t", "т");
        pairs.put("u", "у");
        pairs.put("ŭ", "ў");
        pairs.put("f", "ф");
        pairs.put("ch", "х");
        pairs.put("c", "ц");
        pairs.put("č", "ч");
        pairs.put("š", "ш");
        pairs.put("y", "ы");
        //pairs.put("ь", "");
        pairs.put("e", "э");
        pairs.put("ju", "ю");
        pairs.put("ja", "я");
        pairs.put("ie", "е");
        pairs.put("ĺ", "ль");
        pairs.put("ń", "нь");
        pairs.put("ć", "ць");
        pairs.put("ś", "сь");
        pairs.put("ź", "зь");

        pairsL = new HashMap<>();
        pairsL.put("ĺ", "ль");
        pairsL.put("ie", "е");
        pairsL.put("iu", "ю");
        pairsL.put("io", "ё");
        pairsL.put("ia", "я");
    }

    @Override
    public String convert(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        ArrayList<ParsedElement> elements = parser.parse(text);
        StringBuilder result = new StringBuilder();

        for (ParsedElement current : elements) {
            result.append(current.getDelimiter()).append(convertElement(current));
        }

        return result.toString();
    }

    private String convertElement(ParsedElement current) {
        String convertedValue = advancedReplace(current.getWord());
        convertedValue = transformCase(current.getWordCase(), convertedValue);
        return convertedValue;
    }

    private String advancedReplace(String word) {
        String preparedWord = replaceVowelsAndL(word);
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < preparedWord.length()) {
            char c = preparedWord.charAt(i);
            String two = i + 1 < preparedWord.length() ? preparedWord.substring(i, i + 2) : null;
            if (StringUtilCheck.isNumber(c) || StringUtilCheck.isCyrillicSymbol(c)) {
                result.append(c);
                i++;
            } else if (two != null && pairs.containsKey(two)) {
                // dźvie litary z adnym hukam: ch, ja, je, jo, ju
                result.append(pairs.get(two));
                i += 2;
            } else {
                // litara, jakoj niama ŭ tablicy, zastajecca jak josć
                result.append(pairs.getOrDefault(String.valueOf(c), String.valueOf(c)));
                i++;
            }
        }
        return result.toString();
    }

    private String replaceVowelsAndL(String word) {
        for (Map.Entry<String, String> entry : pairsL.entrySet()) {
            word = word.replace(entry.getKey(), entry.getValue());
        }
        return word;
    }
}
