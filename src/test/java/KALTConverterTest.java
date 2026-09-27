import io.github.heorhipuhachou.orthography.Converter;
import io.github.heorhipuhachou.orthography.Converters;
import io.github.heorhipuhachou.orthography.Orthography;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KALTConverterTest {

    private static final Converter converter = Converters.between(Orthography.KA, Orthography.LT);

    @Test
    public void test() {
        assertEquals("nia toj źjezd da Iŭja", converter.convert("не той з'езд да Іўя"));
        assertEquals("Jana j jon ubačyli śviet.", converter.convert("Яна і ён убачылі свет."));
        assertEquals("kab čałaviek Božy byŭ daskanalnym, da ŭsiakaha dobraha dzieła hatovym", converter.convert("каб чалавек Божы быў дасканальным, да ўсякага добрага дзела гатовым"));
    }
}
