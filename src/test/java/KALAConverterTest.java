import io.github.heorhipuhachou.orthography.converter.KALAConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KALAConverterTest {

    private static final KALAConverter converter = new KALAConverter();

    @Test
    public void test() {
        assertEquals("kab čalaviek Božy byŭ daskanaĺnym, da ŭsiakaha dobraha dziela hatovym", converter.convert("каб чалавек Божы быў дасканальным, да ўсякага добрага дзела гатовым"));
    }
}
