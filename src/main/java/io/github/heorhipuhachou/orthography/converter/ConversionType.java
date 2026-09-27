package io.github.heorhipuhachou.orthography.converter;

/**
 * ŁT - Łacinka Tradycyjnaja
 * LA - Łacinka Aficyjnaja
 * KK - Kiryličny Klasyčny (pravapis)
 * KA - Kiryličny Aficyjny (pravapis)
 * <p>
 * Šlach kanvertacyi (musić pracavać u abodva baki)
 * <p>
 * ŁT <--> KK <--> KA <--> LA
 */
public enum ConversionType {
    KALT(new KALTConverter()), // kiryličny aficyjny -> łacinka tradycyjnaja
    KALA(new KALAConverter()), // kiryličny aficyjny -> łacinka aficyjnaja
    KAKK(new KAKKConverter()), // kiryličny aficyjny -> kiryličny klasyčny

    KKLT(new KKLTConverter()), // kiryličny klasyčny -> łacinka tradycyjnaja
    KKLA(new KKLAConverter()), // kiryličny klasyčny -> łacinka aficyjnaja
    KKKA(new KKKAConverter()), // kiryličny klasyčny -> kiryličny aficyjny

    LTLA(new LTLAConverter()), // łacinka tradycyjnaja -> łacinka aficyjnaja
    LTKK(new LTKKConverter()), // łacinka tradycyjnaja -> kiryličny klasyčny
    LTKA(new LTKAConverter()), // łacinka tradycyjnaja -> kiryličny aficyjny

    LALT(new LALTConverter()), // łacinka aficyjnaja -> łacinka tradycyjnaja
    LAKK(new LAKKConverter()), // łacinka aficyjnaja -> kiryličny klasyčny
    LAKA(new LAKAConverter()); // łacinka aficyjnaja -> kiryličny aficyjny

    public final BaseConverter converter;

    ConversionType(BaseConverter converter) {
        this.converter = converter;
    }
}
