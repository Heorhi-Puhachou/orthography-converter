package io.github.heorhipuhachou.orthography.converter.rule;

import java.util.ArrayList;

public class Softeners {
    private static Softeners single_instance = null;


    private final ArrayList<String> softeners;


    private Softeners() {
        softeners = new ArrayList<>();
        softeners.add("ь");
        softeners.add("я");
        softeners.add("е");
        softeners.add("ю");
        softeners.add("ё");
        softeners.add("і");
    }

    public static Softeners getInstance() {
        if (single_instance == null)
            single_instance = new Softeners();

        return single_instance;
    }

    public static ArrayList<String> getSofteners() {
        return getInstance().softeners;
    }
}
