package io.github.heorhipuhachou.orthography;

import io.github.heorhipuhachou.orthography.converter.KAKKConverter;
import io.github.heorhipuhachou.orthography.converter.KALAConverter;
import io.github.heorhipuhachou.orthography.converter.KKKAConverter;
import io.github.heorhipuhachou.orthography.converter.KKLTConverter;
import io.github.heorhipuhachou.orthography.converter.LAKAConverter;
import io.github.heorhipuhachou.orthography.converter.LTKKConverter;

import java.util.List;
import java.util.Map;

import static io.github.heorhipuhachou.orthography.Orthography.KA;
import static io.github.heorhipuhachou.orthography.Orthography.KK;
import static io.github.heorhipuhachou.orthography.Orthography.LA;
import static io.github.heorhipuhachou.orthography.Orthography.LT;

/**
 * Kanvertary pamiž lubymi dvuma pravapisami.
 * <p>
 * Pravapisy stajać u łancužku, i kanvertacyja idzie ad susieda da susieda:
 * <p>
 * ŁT <--> KK <--> KA <--> LA
 */
public final class Converters {

    private static final List<Orthography> CHAIN = List.of(LT, KK, KA, LA);

    private static final Map<Orthography, Map<Orthography, Converter>> STEPS = Map.of(
            LT, Map.of(KK, new LTKKConverter()),
            KK, Map.of(LT, new KKLTConverter(), KA, new KKKAConverter()),
            KA, Map.of(KK, new KAKKConverter(), LA, new KALAConverter()),
            LA, Map.of(KA, new LAKAConverter()));

    private Converters() {
    }

    public static Converter between(Orthography from, Orthography to) {
        int index = CHAIN.indexOf(from);
        int end = CHAIN.indexOf(to);
        int step = Integer.signum(end - index);

        Converter result = text -> text;
        while (index != end) {
            result = result.andThen(STEPS.get(CHAIN.get(index)).get(CHAIN.get(index + step)));
            index += step;
        }
        return result;
    }
}
