package io.github.heorhipuhachou.orthography.util;

public class ReplacementPair {
    private final String officialSpelling;
    private final String classicSpelling;

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
