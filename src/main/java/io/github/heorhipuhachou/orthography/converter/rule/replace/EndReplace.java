package io.github.heorhipuhachou.orthography.converter.rule.replace;

import io.github.heorhipuhachou.orthography.util.ReplacementPair;

import java.util.ArrayList;

public class EndReplace {
    private static EndReplace single_instance = null;


    private ArrayList<ReplacementPair> endReplace;


    private EndReplace() {
        this.endReplace = new ArrayList<>();
        endReplace.add(new ReplacementPair("метр", "метар"));
        endReplace.add(new ReplacementPair("літр", "літар"));

        //калегіум - калегіюм
        endReplace.add(new ReplacementPair("іум", "іюм"));

        //кампендыум - кампендыюм
        endReplace.add(new ReplacementPair("ыум", "ыюм"));

        //эскадрылья - эскадрыльля
        endReplace.add(new ReplacementPair("лья", "льля"));

        //смяешся - сьмяесься
        endReplace.add(new ReplacementPair("шся", "сься"));
    }

    public static EndReplace getInstance() {
        if (single_instance == null)
            single_instance = new EndReplace();

        return single_instance;
    }

    public static ArrayList<ReplacementPair> getEndReplaces() {
        return getInstance().endReplace;
    }
}
