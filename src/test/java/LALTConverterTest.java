import io.github.heorhipuhachou.orthography.Converter;
import io.github.heorhipuhachou.orthography.Converters;
import io.github.heorhipuhachou.orthography.Orthography;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LALTConverterTest {

    private static final Converter converter = Converters.between(Orthography.LA, Orthography.LT);

    @Test
    public void test() {
        assertEquals("kaval bol sol čałaviek", converter.convert("kavaĺ boĺ soĺ čalaviek"));
        assertEquals("nia toj mentalitet", converter.convert("nie toj mientalitet"));
    }
}
