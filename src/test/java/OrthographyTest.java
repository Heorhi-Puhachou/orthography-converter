import io.github.heorhipuhachou.orthography.Orthography;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrthographyTest {

    @Test
    public void testKnownCodes() {
        assertAll(
                () -> assertEquals(Optional.of(Orthography.LT), Orthography.fromCode("ŁT")),
                () -> assertEquals(Optional.of(Orthography.LT), Orthography.fromCode("łt")),
                () -> assertEquals(Optional.of(Orthography.LA), Orthography.fromCode("LA")),
                () -> assertEquals(Optional.of(Orthography.KK), Orthography.fromCode("kk")),
                () -> assertEquals(Optional.of(Orthography.KA), Orthography.fromCode("Ka"))
        );
    }

    @Test
    public void testUnknownCodes() {
        assertAll(
                () -> assertEquals(Optional.empty(), Orthography.fromCode("XX")),
                () -> assertEquals(Optional.empty(), Orthography.fromCode("LT")),
                () -> assertEquals(Optional.empty(), Orthography.fromCode(""))
        );
    }
}
