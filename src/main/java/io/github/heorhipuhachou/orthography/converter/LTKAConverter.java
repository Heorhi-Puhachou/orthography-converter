package io.github.heorhipuhachou.orthography.converter;


public class LTKAConverter extends BaseConverter {
    public String convert(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        String classicText = (new LTKKConverter()).convert(text);   // ŁT -> KK
        String officialText = (new KKKAConverter()).convert(classicText); // KK -> KA

        return officialText;
    }
}
