import io.github.heorhipuhachou.orthography.converter.LTLAConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LTLAConverterTest {

    private static final LTLAConverter converter = new LTLAConverter();

    @Test
    public void test() {
        assertEquals("kavaĺ daskanaĺnym snieh viasiellie", converter.convert("kaval daskanalnym śnieh viasielle"));
    }
}
