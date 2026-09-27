package io.github.heorhipuhachou.orthography.util;

public class ReplacementPair {
    private String officialSpelling;
    private String classicSpelling;

    public ReplacementPair(String officialSpelling, String classicSpelling) {
        this.officialSpelling = officialSpelling;
        this.classicSpelling = classicSpelling;
    }

    public String getOfficialSpelling() {
        return officialSpelling;
    }

    public String getClassicSpelling() {
        return classicSpelling;
    }
}
