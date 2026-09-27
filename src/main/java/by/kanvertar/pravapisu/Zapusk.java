package by.kanvertar.pravapisu;

import by.kanvertar.pravapisu.converter.*;
import by.kanvertar.pravapisu.pamylki.PamylkaVybaryPravapisu;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;

public class Zapusk {

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


    public static void main(String[] args) throws IOException, PamylkaVybaryPravapisu {
        if (args.length != 4) {
            System.out.println("Niapravilnaja kolkaść arhumentaŭ. Ich pavinna być 4:");
            System.out.println("- pravapis u fajle, jaki budzie kanvertavacca (ŁT, LA, KK, KA)");
            System.out.println("  ŁT - Łacinka Tradycyjnaja");
            System.out.println("  LA - Łacinka Aficyjnaja");
            System.out.println("  KK - Kiryličny Klasyčny (pravapis)");
            System.out.println("  KA - Kirylica Aficyjny (pravapis)");
            System.out.println("- šlach da txt-fajła, jaki budzie kanvertavacca");
            System.out.println("- pravapis novaha fajła (ŁT, LA, KK, KA)");
            System.out.println("- šlach da  novaha fajła");
            System.out.println("\nprykład:");
            System.out.println("java -jar converter.jar KA krynica.txt ŁT vynik.txt\n");
        } else {
            String inputStyle = args[0];
            String inputPath = args[1];
            String outputStyle = args[2];
            String outputPath = args[3];
            checkAndConvert(inputStyle, inputPath, outputStyle, outputPath);
        }
    }

    static void checkAndConvert(
            String inputStyle,
            String inputPath,
            String outputStyle,
            String outputPath) throws IOException, PamylkaVybaryPravapisu {
        if (!validStyle(inputStyle)) {
            System.out.println("Niapravilny styl uvachodnaha fajła.");
        } else if (!validStyle(outputStyle)) {
            System.out.println("Niapravilny styl vychodnaha fajła.");
        } else {
            BazavyKanvertar converter = getConverterByStyles(inputStyle, outputStyle);
            convertToFile(inputPath, outputPath, converter);
        }
    }

    static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, StandardCharsets.UTF_8);
    }

    static void writeToFile(String output, String path) throws IOException {
        FileWriter myWriter = new FileWriter(path);
        myWriter.write(output);
        myWriter.close();
    }

    static void convertToFile(String inputPath, String outputPath, BazavyKanvertar converter) throws IOException {
        String input = readFile(inputPath);
        System.out.println(input);
        String output = converter.kanvertavać(input);
        System.out.println(output);
        writeToFile(output, outputPath);
    }

    static boolean validStyle(String style) {
        return style.equalsIgnoreCase("ŁT")
                || style.equalsIgnoreCase("LA")
                || style.equalsIgnoreCase("KK")
                || style.equalsIgnoreCase("KA");
    }

    public static boolean hetaValidnyKodPravapisy(String kodPravapisy) {
        for (VidKanvertacyi c : VidKanvertacyi.values()) {
            if (c.name().equals(kodPravapisy)) {
                return true;
            }
        }
        return false;
    }

    static BazavyKanvertar getConverterByStyles(String inputStyle, String outputStyle) throws PamylkaVybaryPravapisu {
        if (inputStyle.length() != 2 || outputStyle.length() != 2) {
            throw new PamylkaVybaryPravapisu();
        }

        String kod = toEnumCode(inputStyle) + toEnumCode(outputStyle);
        if (!hetaValidnyKodPravapisy(kod)) {
            throw new PamylkaVybaryPravapisu();
        }

        return VidKanvertacyi.valueOf(kod).kanvertar;
    }

    // Karystalnik piša "ŁT" i ŭ luboj veličyni litar, a ŭ VidKanvertacyi kody zapisanyja jak "LT"
    static String toEnumCode(String style) {
        return style.toUpperCase(Locale.ROOT).replace('Ł', 'L');
    }
}
