import io.github.heorhipuhachou.orthography.converter.KKKAConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KKKAConverterTest {

    private static final KKKAConverter converter = new KKKAConverter();

    @Test
    public void test() {
        assertEquals("Не там Еўропа...", converter.convert("Ня там Эўропа..."));
    }

    @Test
    public void testSoftSign() {
        assertEquals("пасля", converter.convert("пасьля"));
    }

    @Test
    public void testEnding() {
        assertEquals("кампендыум", converter.convert("кампендыюм"));
        assertEquals("калегіум", converter.convert("калегіюм"));
    }

    @Test
    public void testSoftSignInDoubles() {
        assertEquals("вяселле", converter.convert("вясельле"));
    }

    @Test
    public void testNoChange() {
        assertEquals("карыстальнік", converter.convert("карыстальнік"));
    }

    @Test
    public void testDZ() {
        assertEquals("суддзя", converter.convert("судзьдзя"));
    }

    @Test
    public void testJa() {
        assertEquals("класічны", converter.convert("клясычны"));
    }

    @Test
    public void testSoftSignAfterZ() {
        assertEquals("праз яе", converter.convert("празь яе"));
        assertEquals("з яе", converter.convert("зь яе"));
        assertEquals("з'еў", converter.convert("зьеў"));
        assertEquals("з'езд", converter.convert("зьезд"));
    }

}
