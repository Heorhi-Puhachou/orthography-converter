package io.github.heorhipuhachou.orthography.converter.rule.replace;

import io.github.heorhipuhachou.orthography.util.ReplacementPair;

import java.util.ArrayList;

public class StartReplace {
    private static StartReplace single_instance = null;


    private final ArrayList<ReplacementPair> startReplace;


    private StartReplace() {
        this.startReplace = new ArrayList<>();
        startReplace.add(new ReplacementPair("бельгі", "бэльгі"));
        startReplace.add(new ReplacementPair("ірланд", "ірлянд"));
        startReplace.add(new ReplacementPair("люксембург", "люксэмбург"));
        startReplace.add(new ReplacementPair("нідэрланд", "нідэрлянд"));
        startReplace.add(new ReplacementPair("швейцар", "швайцар"));
        startReplace.add(new ReplacementPair("швецы", "швэцы"));
        startReplace.add(new ReplacementPair("амерык", "амэрык"));

        startReplace.add(new ReplacementPair("аперац", "апэрац"));
        startReplace.add(new ReplacementPair("інструмент", "інструмэнт"));
        startReplace.add(new ReplacementPair("сервер", "сэрвэр"));
        startReplace.add(new ReplacementPair("сітуац", "сытуац"));
        startReplace.add(new ReplacementPair("спец", "спэц"));
        startReplace.add(new ReplacementPair("псіх", "псых"));
        startReplace.add(new ReplacementPair("імпер", "імпэр"));
        startReplace.add(new ReplacementPair("шаблон", "шаблён"));
        startReplace.add(new ReplacementPair("лакал", "лякал"));
        startReplace.add(new ReplacementPair("лабарат", "лябарат"));
        startReplace.add(new ReplacementPair("канвенц", "канвэнц"));
        startReplace.add(new ReplacementPair("эфект", "эфэкт"));
        startReplace.add(new ReplacementPair("механі", "мэхані"));
    }

    public static StartReplace getInstance() {
        if (single_instance == null)
            single_instance = new StartReplace();

        return single_instance;
    }

    public static ArrayList<ReplacementPair> getStartReplaces() {
        return getInstance().startReplace;
    }
}
