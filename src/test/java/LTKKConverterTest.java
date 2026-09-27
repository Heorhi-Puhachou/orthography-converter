import io.github.heorhipuhachou.orthography.Converter;
import io.github.heorhipuhachou.orthography.Converters;
import io.github.heorhipuhachou.orthography.Orthography;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LTKKConverterTest {

    private static final Converter converter = Converters.between(Orthography.LT, Orthography.KK);

    @Test
    public void test() {
        assertEquals("каб чалавек Божы быў дасканальным, да ўсякага добрага дзела гатовым", converter.convert("kab čałaviek Božy byŭ daskanalnym, da ŭsiakaha dobraha dzieła hatovym"));
        assertEquals("каваль дасканальным сьнег", converter.convert("kaval daskanalnym śnieh"));
    }

    @Test
    public void testCh() {
        assertEquals("хата", converter.convert("chata"));
    }

    @Test
    public void testJVowels() {
        assertAll(
                () -> assertEquals("яна", converter.convert("jana")),
                () -> assertEquals("ён", converter.convert("jon")),
                () -> assertEquals("юнак", converter.convert("junak")),
                () -> assertEquals("ехаць", converter.convert("jechać"))
        );
    }

    @Test
    public void testUnknownLetter() {
        assertEquals("Wікі", converter.convert("Wiki"));
    }
}
