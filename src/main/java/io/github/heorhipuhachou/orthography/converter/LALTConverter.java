package io.github.heorhipuhachou.orthography.converter;


public class LALTConverter extends BaseConverter {
    public String convert(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        String officialText = (new LAKAConverter()).convert(text);   // LA -> KA
        String classicText = (new KAKKConverter()).convert(officialText); // KA -> KK
        String latinText = (new KKLTConverter()).convert(classicText); // KK -> LT

        return latinText;
    }
}
