import io.github.heorhipuhachou.orthography.converter.KKLTConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KKLTConverterTest {

    private static final KKLTConverter converter = new KKLTConverter();

    @Test
    public void test() {
        assertEquals("Lahienda Zeldy", converter.convert("Лягенда Зэльды"));
    }

    @Test
    public void testN() {
        assertEquals("Lahienda Zeldy", converter.convert("Лягенда Зэльды"));
    }

    @Test
    public void testL() {
        assertEquals("Luk", converter.convert("Люк"));
        assertEquals("Ła ła ła", converter.convert("Ла ла ла"));
        assertEquals("Lalka", converter.convert("Лялька"));
        assertEquals("Łyč Linka", converter.convert("Лыч Лінка"));
    }

    @Test
    public void testZ() {
        assertEquals("Žyćcio", converter.convert("Жыцьцё"));
    }
}
