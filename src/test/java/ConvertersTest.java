import io.github.heorhipuhachou.orthography.Converters;
import io.github.heorhipuhachou.orthography.Orthography;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConvertersTest {

    @Test
    public void testSameOrthographyKeepsText() {
        assertEquals("Не там Еўропа...", Converters.between(Orthography.KA, Orthography.KA).convert("Не там Еўропа..."));
    }

    @Test
    public void testLongestPath() {
        // ŁT -> KK -> KA -> LA
        assertEquals("kavaĺ daskanaĺnym snieh viasiellie",
                Converters.between(Orthography.LT, Orthography.LA).convert("kaval daskanalnym śnieh viasielle"));
    }
}
