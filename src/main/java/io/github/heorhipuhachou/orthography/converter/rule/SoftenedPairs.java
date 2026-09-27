package io.github.heorhipuhachou.orthography.converter.rule;

import java.util.ArrayList;

public class SoftenedPairs {


    private static SoftenedPairs single_instance = null;


    private final ArrayList<String> softenedPairs;


    private SoftenedPairs() {
        softenedPairs = new ArrayList<>();
        for (int i = 0; i < SofteningConsonants.getSofteningConsonants().size(); i++) {
            for (int j = 0; j < Softeners.getSofteners().size(); j++) {
                softenedPairs.add(SofteningConsonants.getSofteningConsonants().get(i) + Softeners.getSofteners().get(j));
            }
        }
    }

    public static SoftenedPairs getInstance() {
        if (single_instance == null)
            single_instance = new SoftenedPairs();

        return single_instance;
    }

    public static ArrayList<String> getSoftenedPairs() {
        return getInstance().softenedPairs;
    }
}
