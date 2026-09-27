package io.github.heorhipuhachou.orthography.converter;


public class LAKKConverter extends BaseConverter {
    public String convert(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        String officialText = (new LAKAConverter()).convert(text);   // LA -> KA
        // KA -> KK

        return (new KAKKConverter()).convert(officialText);
    }
}
