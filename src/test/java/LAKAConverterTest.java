import io.github.heorhipuhachou.orthography.converter.LAKAConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LAKAConverterTest {

    private static final LAKAConverter converter = new LAKAConverter();

    @Test
    public void test() {
        assertEquals("каваль боль соль чалавек", converter.convert("kavaĺ boĺ soĺ čalaviek"));
    }
}
