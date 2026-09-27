import io.github.heorhipuhachou.orthography.converter.LALTConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LALTConverterTest {

    private static final LALTConverter converter = new LALTConverter();

    @Test
    public void test() {
        assertEquals("kaval bol sol čałaviek", converter.convert("kavaĺ boĺ soĺ čalaviek"));
        assertEquals("nia toj mentalitet", converter.convert("nie toj mientalitet"));
    }
}
