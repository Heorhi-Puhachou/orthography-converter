import io.github.heorhipuhachou.orthography.Converter;
import io.github.heorhipuhachou.orthography.Converters;
import io.github.heorhipuhachou.orthography.Orthography;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KALAConverterTest {

    private static final Converter converter = Converters.between(Orthography.KA, Orthography.LA);

    @Test
    public void test() {
        assertEquals("kab čalaviek Božy byŭ daskanaĺnym, da ŭsiakaha dobraha dziela hatovym", converter.convert("каб чалавек Божы быў дасканальным, да ўсякага добрага дзела гатовым"));
    }
}
