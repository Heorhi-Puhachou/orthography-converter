import io.github.heorhipuhachou.orthography.converter.LAKAConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LAKAConverterTest {

    private static final LAKAConverter converter = new LAKAConverter();

    @Test
    public void test() {
        assertEquals("каваль боль соль чалавек", converter.convert("kavaĺ boĺ soĺ čalaviek"));
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
                () -> assertEquals("ехаць", converter.convert("jechać")),
                () -> assertEquals("Еўропа", converter.convert("Jeŭropa"))
        );
    }

    @Test
    public void testUnknownLetter() {
        assertEquals("Wікі", converter.convert("Wiki"));
    }
}
