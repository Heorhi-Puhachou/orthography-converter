package io.github.heorhipuhachou.orthography.converter.rule.replace;

import io.github.heorhipuhachou.orthography.util.ReplacementPair;

import java.util.ArrayList;

public class TemplateReplace {
    private static TemplateReplace single_instance = null;


    private final ArrayList<ReplacementPair> templateReplace;


    private TemplateReplace() {
        this.templateReplace = new ArrayList<>();
        templateReplace.add(new ReplacementPair("амерык", "амэрык"));
        templateReplace.add(new ReplacementPair("анверт", "анвэрт"));
        templateReplace.add(new ReplacementPair("артапед", "артапэд"));
        templateReplace.add(new ReplacementPair("аспект", "аспэкт"));
        templateReplace.add(new ReplacementPair("арфаграф", "артаграф"));
        templateReplace.add(new ReplacementPair("арыстоцель", "арыстотэль"));
        templateReplace.add(new ReplacementPair("валанцёр", "валянтэр"));
        templateReplace.add(new ReplacementPair("версі", "вэрсі"));
        templateReplace.add(new ReplacementPair("вулкан", "вулькан"));
        templateReplace.add(new ReplacementPair("в’етнам", "віетнам"));
        templateReplace.add(new ReplacementPair("гаус", "гаўс"));
        templateReplace.add(new ReplacementPair("Генры", "Гэнры"));
        templateReplace.add(new ReplacementPair("глам", "глям"));
        templateReplace.add(new ReplacementPair("гласар", "глясар"));
        templateReplace.add(new ReplacementPair("візіт", "візыт"));
        templateReplace.add(new ReplacementPair("каментарый", "камэнтар"));
        templateReplace.add(new ReplacementPair("мент", "мэнт"));
        templateReplace.add(new ReplacementPair("донья", "доньня"));
        templateReplace.add(new ReplacementPair("еўр", "эўр"));
        templateReplace.add(new ReplacementPair("іерогліф", "герогліф"));
        templateReplace.add(new ReplacementPair("каталог", "каталёг"));
        templateReplace.add(new ReplacementPair("класіч", "клясыч"));
        templateReplace.add(new ReplacementPair("клуб", "клюб"));
        templateReplace.add(new ReplacementPair("лагіч", "лягіч"));
        templateReplace.add(new ReplacementPair("лампада", "лямпада"));
        templateReplace.add(new ReplacementPair("лейкацыт", "леўкацыт"));
        templateReplace.add(new ReplacementPair("логік", "лёгік"));
        templateReplace.add(new ReplacementPair("логія", "лёгія"));
        templateReplace.add(new ReplacementPair("мекка", "мэка"));
        templateReplace.add(new ReplacementPair("метад", "мэтад"));
        templateReplace.add(new ReplacementPair("механ", "мэхан"));
        templateReplace.add(new ReplacementPair("мільянер", "мільянэр"));
        templateReplace.add(new ReplacementPair("менеджэр", "мэнэджар"));
        templateReplace.add(new ReplacementPair("музей", "музэй"));
        templateReplace.add(new ReplacementPair("мушкіцёр", "мушкітэр"));
        templateReplace.add(new ReplacementPair("пазіцы", "пазыцы"));
        templateReplace.add(new ReplacementPair("партнёр", "партнэр"));
        templateReplace.add(new ReplacementPair("плутон", "плютон"));
        templateReplace.add(new ReplacementPair("прэзент", "прэзэнт"));
        templateReplace.add(new ReplacementPair("рыдыус", "радыюс"));
        templateReplace.add(new ReplacementPair("рэклам", "рэклям"));
        templateReplace.add(new ReplacementPair("саліцёр", "слітэр"));
        templateReplace.add(new ReplacementPair("сегм", "сэгм"));
        templateReplace.add(new ReplacementPair("сесія", "сэсія"));
        templateReplace.add(new ReplacementPair("сесію", "сэсію"));
        templateReplace.add(new ReplacementPair("сігнал", "сыгнал"));
        templateReplace.add(new ReplacementPair("сістэм", "сыстэм"));
        templateReplace.add(new ReplacementPair("фунікулёр", "фунікулер"));
        templateReplace.add(new ReplacementPair("фальклор", "фальклёр"));
        templateReplace.add(new ReplacementPair("шоу", "шоў"));
        templateReplace.add(new ReplacementPair("фект", "фэкт"));
    }

    public static TemplateReplace getInstance() {
        if (single_instance == null)
            single_instance = new TemplateReplace();

        return single_instance;
    }

    public static ArrayList<ReplacementPair> getTemplateReplaces() {
        return getInstance().templateReplace;
    }
}
