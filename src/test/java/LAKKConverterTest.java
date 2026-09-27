import io.github.heorhipuhachou.orthography.converter.LAKKConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LAKKConverterTest {

    private static final LAKKConverter converter = new LAKKConverter();

    @Test
    public void test() {
        assertEquals("ня той мэнталітэт", converter.convert("nie toj mientalitet"));
    }
}
