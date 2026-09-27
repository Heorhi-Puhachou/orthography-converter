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


public class KAKKConverter extends BaseConverter {

    private Parser parser;

    public KAKKConverter() {
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
                ParsedElement next = getNextElement(elements, index);
                result.append(elements.get(index).getDelimiter()).append(convertElement(prev, current, next));
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

    private ParsedElement getNextElement(ArrayList<ParsedElement> elements, int index) {
        if (index < elements.size() - 1) {
            return elements.get(index + 1);
        } else {
            return null;
        }
    }

    private String convertElement(ParsedElement prev, ParsedElement current, ParsedElement next) {
        String convertedValue = checkI(prev, current.getWord(), current.getDelimiter());
        convertedValue = checkZ(convertedValue, next);
        convertedValue = checkApost(convertedValue);
        convertedValue = checkNe(convertedValue, next);
        convertedValue = checkBez(convertedValue, next);
        convertedValue = templateReplace(convertedValue);
        convertedValue = replaceStart(convertedValue);
        convertedValue = replaceEnd(convertedValue);
        convertedValue = checkSoftSign(convertedValue);
        convertedValue = checkSoftSignForDoubles(convertedValue);
        convertedValue = checkDz(convertedValue);
        convertedValue = transformCase(current.getWordCase(), convertedValue);
        if (!transformCase(current.getWordCase(), current.getWord()).equals(convertedValue)) {
            System.out.println(transformCase(current.getWordCase(), current.getWord()) + " -> " + convertedValue);
        }
        return convertedValue;
    }

    // і -> й
    private String checkI(ParsedElement prev, String current, String delimiter) {
        if (prev != null && current.equals("і") && delimiter.equals(" ")) {
            String lastPrevSymbol = StringUtilGet.getLastSymbol(prev.getWord());
            if (StringUtilCheck.isVowel(lastPrevSymbol)) {
                return "й";
            }
        }
        return current;
    }

    // з'езд -> зьезд
    private String checkApost(String current) {
        if (current.contains("з'")) {
            return current.replace("з'","зь");
        }
        return current;
    }

    // з -> зь
    // праз -> празь
    private String checkZ(String current, ParsedElement next) {
        if (next != null
                && next.getDelimiter().equals(" ")
                && current.equals("з")) {
            for (int i = 0; i < SoftenedPairs.getSoftenedPairs().size(); i++) {
                if (next.getWord().startsWith(SoftenedPairs.getSoftenedPairs().get(i))) {
                    return "зь";
                }
            }
            for (int i = 0; i < Softeners.getSofteners().size(); i++) {
                if (next.getWord().startsWith(Softeners.getSofteners().get(i))) {
                    return "зь";
                }
            }
        }

        if (next != null
                && next.getDelimiter().equals(" ")
                && current.equals("праз")) {
            for (int i = 0; i < SoftenedPairs.getSoftenedPairs().size(); i++) {
                if (next.getWord().startsWith(SoftenedPairs.getSoftenedPairs().get(i))) {
                    return "празь";
                }
            }
            for (int i = 0; i < Softeners.getSofteners().size(); i++) {
                if (next.getWord().startsWith(Softeners.getSofteners().get(i))) {
                    return "празь";
                }
            }
        }

        return current;
    }

    // не -> ня
    private String checkNe(String current, ParsedElement next) {
        if (current.equals("не")
                && next != null
                && next.getDelimiter().equals(" ")
                && StringUtilCheck.isFirstSyllableStressed(next.getWord())) {
            return "ня";
        }

        return current;
    }

    // без -> бяз
    private String checkBez(String current, ParsedElement next) {
        if (current.equals("без")
                && next != null
                && next.getDelimiter().equals(" ")
                && StringUtilCheck.isFirstSyllableStressed(next.getWord())) {
            return "бяз";
        }
        return current;
    }


    private String checkSoftSign(String in) {
        for (int i = 0; i < SoftenableConsonants.getSoftenableConsonants().size(); i++) {
            for (int j = 0; j < SoftenedPairs.getSoftenedPairs().size(); j++) {
                String officialVersionString = SoftenableConsonants.getSoftenableConsonants().get(i) + SoftenedPairs.getSoftenedPairs().get(j);
                String classicVersionString = SoftenableConsonants.getSoftenableConsonants().get(i) + "ь" + SoftenedPairs.getSoftenedPairs().get(j);
                in = in.replace(officialVersionString, classicVersionString);
            }
        }
        return in;
    }

    private String checkSoftSignForDoubles(String in) {
        for (int i = 0; i < DoubleSoftConsonants.getDoubleSoftConsonants().size(); i++) {
            for (int j = 0; j < Softeners.getSofteners().size(); j++) {
                String officialVersionString = DoubleSoftConsonants.getDoubleSoftConsonants().get(i) + DoubleSoftConsonants.getDoubleSoftConsonants().get(i) + Softeners.getSofteners().get(j);
                String classicVersionString = DoubleSoftConsonants.getDoubleSoftConsonants().get(i) + "ь" + DoubleSoftConsonants.getDoubleSoftConsonants().get(i) + Softeners.getSofteners().get(j);
                in = in.replace(officialVersionString, classicVersionString);
            }
        }
        return in;
    }

    private String checkDz(String in) {
        for (int j = 0; j < Softeners.getSofteners().size(); j++) {
            String officialVersionString = "ддз" + Softeners.getSofteners().get(j);
            String classicVersionString = "дзьдз" + Softeners.getSofteners().get(j);
            in = in.replace(officialVersionString, classicVersionString);
        }
        return in;
    }

    private String templateReplace(String in) {
        for (ReplacementPair pair : TemplateReplace.getTemplateReplaces()) {
            in = in.replace(pair.getOfficialSpelling(), pair.getClassicSpelling());
        }
        return in;
    }

    private String replaceEnd(String word) {
        for (ReplacementPair pair : EndReplace.getEndReplaces()) {
            if (word.endsWith(pair.getOfficialSpelling())) {
                return word.replace(pair.getOfficialSpelling(), pair.getClassicSpelling());
            }
        }
        return word;
    }

    private String replaceStart(String word) {
        for (ReplacementPair pair : StartReplace.getStartReplaces()) {
            if (word.startsWith(pair.getOfficialSpelling())) {
                return word.replace(pair.getOfficialSpelling(), pair.getClassicSpelling());
            }
        }
        return word;
    }
}
