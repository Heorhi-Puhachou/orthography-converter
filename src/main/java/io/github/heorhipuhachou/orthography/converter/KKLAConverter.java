package io.github.heorhipuhachou.orthography.converter;


public class KKLAConverter extends BaseConverter {
    public String convert(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        String officialText = (new KKKAConverter()).convert(text);

        return (new KALAConverter()).convert(officialText);
    }
}
