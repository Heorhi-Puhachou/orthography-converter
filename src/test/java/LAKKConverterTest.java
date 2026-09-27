import io.github.heorhipuhachou.orthography.Converter;
import io.github.heorhipuhachou.orthography.Converters;
import io.github.heorhipuhachou.orthography.Orthography;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LAKKConverterTest {

    private static final Converter converter = Converters.between(Orthography.LA, Orthography.KK);

    @Test
    public void test() {
        assertEquals("ня той мэнталітэт", converter.convert("nie toj mientalitet"));
    }
}
