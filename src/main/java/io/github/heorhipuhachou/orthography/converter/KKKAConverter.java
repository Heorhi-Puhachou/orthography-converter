package io.github.heorhipuhachou.orthography.converter;


import io.github.heorhipuhachou.orthography.converter.rule.DoubleSoftConsonants;
import io.github.heorhipuhachou.orthography.converter.rule.SoftenableConsonants;
import io.github.heorhipuhachou.orthography.converter.rule.SoftenedPairs;
import io.github.heorhipuhachou.orthography.converter.rule.Softeners;
import io.github.heorhipuhachou.orthography.converter.rule.replace.EndReplace;
import io.github.heorhipuhachou.orthography.converter.rule.replace.StartReplace;
import io.github.heorhipuhachou.orthography.converter.rule.replace.TemplateReplace;
import io.github.heorhipuhachou.orthography.parser.ParsedElement;
import io.github.heorhipuhachou.orthography.parser.Parser;
import io.github.heorhipuhachou.orthography.util.ReplacementPair;
import io.github.heorhipuhachou.orthography.util.StringUtilCheck;
import io.github.heorhipuhachou.orthography.util.StringUtilGet;
import io.github.heorhipuhachou.orthography.util.WordCase;

import java.util.ArrayList;

import static io.github.heorhipuhachou.orthography.util.StringUtilTransform.transformCase;


public class KKKAConverter extends BaseConverter {

    private final Parser parser;

    public KKKAConverter() {
        this.parser = new Parser();
    }

    public String convert(String text) {

        if (text == null || text.isEmpty()) {
            return text;
        }

        ArrayList<ParsedElement> elements = parser.parse(text);

        StringBuilder result = new StringBuilder();

        for (int index = 0; index < elements.size(); index++) {
            ParsedElement current = elements.get(index);
            if (StringUtilCheck.isEngWord(current.getOriginalWord()) || current.getWordCase() == WordCase.OTHER) {
                result.append(current.getDelimiter()).append(current.getOriginalWord());
            } else {
                ParsedElement prev = getPrevElement(elements, index);
                result.append(elements.get(index).getDelimiter()).append(convertElement(prev, current));
            }
        }
        return result.toString();
    }

    private ParsedElement getPrevElement(ArrayList<ParsedElement> elements, int index) {
        if (index > 0) {
            return elements.get(index - 1);
        } else {
            return null;
        }
    }

    private String convertElement(ParsedElement prev, ParsedElement current) {
        String convertedValue = checkI(prev, current.getWord(), current.getDelimiter());
        convertedValue = checkDz(convertedValue);
        convertedValue = checkApost(convertedValue);
        convertedValue = checkZ(convertedValue);
        convertedValue = checkNe(convertedValue);
        convertedValue = checkBez(convertedValue);
        convertedValue = templateReplace(convertedValue);
        convertedValue = replaceStart(convertedValue);
        convertedValue = replaceEnd(convertedValue);
        convertedValue = checkSoftSign(convertedValue);
        convertedValue = checkSoftSignForDoubles(convertedValue);
        convertedValue = transformCase(current.getWordCase(), convertedValue);
        if (!transformCase(current.getWordCase(), current.getWord()).equals(convertedValue)) {
            System.out.println(transformCase(current.getWordCase(), current.getWord()) + " -> " + convertedValue);
        }
        return convertedValue;
    }

    // й -> і
    private String checkI(ParsedElement prev, String current, String delimiter) {
        if (prev != null && current.equals("й") && delimiter.equals(" ")) {
            String lastPrevSymbol = StringUtilGet.getLastSymbol(prev.getWord());
            if (StringUtilCheck.isVowel(lastPrevSymbol)) {
                return "і";
            }
        }
        return current;
    }

    //  зьезд -> з'езд
    private String checkApost(String current) {
        if (current.length() > current.indexOf("зь") + 2
                && current.contains("зь")
                && Softeners.getSofteners().contains(current.substring(current.indexOf("зь") + 2, current.indexOf("зь") + 3))) {
            return current.replace("зь", "з'");
        }
        return current;
    }

    // зь -> з
    // празь -> праз
    private String checkZ(String current) {
        if (current.equals("зь")) {
            return "з";
        }
        if (current.equals("празь")) {
            return "праз";
        }
        return current;
    }

    // ня -> не
    private String checkNe(String current) {
        if (current.equals("ня")) {
            return "не";
        }

        return current;
    }

    // без -> бяз
    private String checkBez(String current) {
        if (current.equals("бяз")) {
            return "без";
        }
        return current;
    }


    private String checkSoftSign(String in) {
        for (int i = 0; i < SoftenableConsonants.getSoftenableConsonants().size(); i++) {
            for (int j = 0; j < SoftenedPairs.getSoftenedPairs().size(); j++) {
                String officialText = SoftenableConsonants.getSoftenableConsonants().get(i) + SoftenedPairs.getSoftenedPairs().get(j);
                String classicText = SoftenableConsonants.getSoftenableConsonants().get(i) + "ь" + SoftenedPairs.getSoftenedPairs().get(j);
                in = in.replace(classicText, officialText);
            }
        }
        return in;
    }

    private String checkSoftSignForDoubles(String in) {
        for (int i = 0; i < DoubleSoftConsonants.getDoubleSoftConsonants().size(); i++) {
            for (int j = 0; j < Softeners.getSofteners().size(); j++) {
                String officialText = DoubleSoftConsonants.getDoubleSoftConsonants().get(i) + DoubleSoftConsonants.getDoubleSoftConsonants().get(i) + Softeners.getSofteners().get(j);
                String classicText = DoubleSoftConsonants.getDoubleSoftConsonants().get(i) + "ь" + DoubleSoftConsonants.getDoubleSoftConsonants().get(i) + Softeners.getSofteners().get(j);
                in = in.replace(classicText, officialText);
            }
        }
        return in;
    }

    //дзьдз -> ддз
    private String checkDz(String in) {
        return in.replace("дзьдз", "ддз");
    }

    private String templateReplace(String in) {
        for (ReplacementPair pair : TemplateReplace.getTemplateReplaces()) {
            in = in.replace(pair.getClassicSpelling(), pair.getOfficialSpelling());
        }
        return in;
    }

    private String replaceEnd(String word) {
        for (ReplacementPair pair : EndReplace.getEndReplaces()) {
            if (word.endsWith(pair.getClassicSpelling())) {
                return word.replace(pair.getClassicSpelling(), pair.getOfficialSpelling());
            }
        }
        return word;
    }

    private String replaceStart(String word) {
        for (ReplacementPair pair : StartReplace.getStartReplaces()) {
            if (word.startsWith(pair.getClassicSpelling())) {
                return word.replace(pair.getClassicSpelling(), pair.getOfficialSpelling());
            }
        }
        return word;
    }
}
