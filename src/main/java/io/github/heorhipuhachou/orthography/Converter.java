package io.github.heorhipuhachou.orthography;

/**
 * Kanvertuje tekst z adnaho pravapisu ŭ inšy.
 */
@FunctionalInterface
public interface Converter {

    String convert(String text);

    /**
     * Spačatku hety kanvertar, potym {@code next}.
     */
    default Converter andThen(Converter next) {
        return text -> next.convert(convert(text));
    }
}
