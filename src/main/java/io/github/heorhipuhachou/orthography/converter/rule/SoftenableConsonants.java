package io.github.heorhipuhachou.orthography.converter.rule;

import java.util.ArrayList;

public class SoftenableConsonants {
    private static SoftenableConsonants single_instance = null;


    private final ArrayList<String> softenableConsonants;


    private SoftenableConsonants() {
        softenableConsonants = new ArrayList<>();
        softenableConsonants.add("с");
        softenableConsonants.add("ц");
        softenableConsonants.add("н");
        softenableConsonants.add("з");
        softenableConsonants.add("дз");
    }

    public static SoftenableConsonants getInstance() {
        if (single_instance == null)
            single_instance = new SoftenableConsonants();

        return single_instance;
    }

    public static ArrayList<String> getSoftenableConsonants() {
        return getInstance().softenableConsonants;
    }
}
