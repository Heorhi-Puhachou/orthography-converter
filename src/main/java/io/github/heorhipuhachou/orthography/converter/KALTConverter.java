package io.github.heorhipuhachou.orthography.converter;


public class KALTConverter extends BaseConverter {
    public String convert(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        String classicText = (new KAKKConverter()).convert(text);

        return (new KKLTConverter()).convert(classicText);
    }
}
