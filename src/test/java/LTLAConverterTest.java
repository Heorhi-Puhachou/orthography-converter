import io.github.heorhipuhachou.orthography.Converter;
import io.github.heorhipuhachou.orthography.Converters;
import io.github.heorhipuhachou.orthography.Orthography;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LTLAConverterTest {

    private static final Converter converter = Converters.between(Orthography.LT, Orthography.LA);

    @Test
    public void test() {
        assertEquals("kavaĺ daskanaĺnym snieh viasiellie", converter.convert("kaval daskanalnym śnieh viasielle"));
    }
}
