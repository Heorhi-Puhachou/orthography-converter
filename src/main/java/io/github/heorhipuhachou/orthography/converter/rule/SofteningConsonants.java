package io.github.heorhipuhachou.orthography.converter.rule;

import java.util.ArrayList;

public class SofteningConsonants {
    private static SofteningConsonants single_instance = null;


    private final ArrayList<String> softeningConsonants;


    private SofteningConsonants() {
        softeningConsonants = new ArrayList<>();
        softeningConsonants.add("с");
        softeningConsonants.add("п");
        softeningConsonants.add("б");
        softeningConsonants.add("в");
        softeningConsonants.add("ц");
        softeningConsonants.add("н");
        softeningConsonants.add("м");
        softeningConsonants.add("л");
        softeningConsonants.add("з");
        softeningConsonants.add("дз");
    }

    public static SofteningConsonants getInstance() {
        if (single_instance == null)
            single_instance = new SofteningConsonants();

        return single_instance;
    }

    public static ArrayList<String> getSofteningConsonants() {
        return getInstance().softeningConsonants;
    }
}
