package io.github.heorhipuhachou.orthography.converter.rule;

import java.util.ArrayList;

public class DoubleSoftConsonants {
    private static DoubleSoftConsonants single_instance = null;

    private final ArrayList<String> doubleSoftConsonants;

    private DoubleSoftConsonants() {
        doubleSoftConsonants = new ArrayList<>();
        doubleSoftConsonants.add("с");
        doubleSoftConsonants.add("ц");
        doubleSoftConsonants.add("н");
        doubleSoftConsonants.add("з");
        doubleSoftConsonants.add("л");
    }

    public static DoubleSoftConsonants getInstance() {
        if (single_instance == null)
            single_instance = new DoubleSoftConsonants();
        return single_instance;
    }

    public static ArrayList<String> getDoubleSoftConsonants() {
        return getInstance().doubleSoftConsonants;
    }
}
