package io.github.heorhipuhachou.orthography;

import java.util.Optional;

/**
 * Pravapisy biełaruskaj movy.
 * <p>
 * Nazva kanstanty — ASCII, kod — toje, što piša karystalnik (napr. "ŁT").
 */
public enum Orthography {
    LT("ŁT"), // Łacinka Tradycyjnaja
    KK("KK"), // Kiryličny Klasyčny
    KA("KA"), // Kiryličny Aficyjny
    LA("LA"); // Łacinka Aficyjnaja

    private final String code;

    Orthography(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }

    /**
     * Pravapis pa kodzie, u luboj veličyni litar ("ŁT", "łt", "ka").
     */
    public static Optional<Orthography> fromCode(String code) {
        for (Orthography orthography : values()) {
            if (orthography.code.equalsIgnoreCase(code)) {
                return Optional.of(orthography);
            }
        }
        return Optional.empty();
    }
}
