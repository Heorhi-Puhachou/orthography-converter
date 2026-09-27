package io.github.heorhipuhachou.orthography;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length != 4) {
            System.out.println("Niapravilnaja kolkaść arhumentaŭ. Ich pavinna być 4:");
            System.out.println("- pravapis u fajle, jaki budzie kanvertavacca (ŁT, LA, KK, KA)");
            System.out.println("  ŁT - Łacinka Tradycyjnaja");
            System.out.println("  LA - Łacinka Aficyjnaja");
            System.out.println("  KK - Kiryličny Klasyčny (pravapis)");
            System.out.println("  KA - Kiryličny Aficyjny (pravapis)");
            System.out.println("- šlach da txt-fajła, jaki budzie kanvertavacca");
            System.out.println("- pravapis novaha fajła (ŁT, LA, KK, KA)");
            System.out.println("- šlach da  novaha fajła");
            System.out.println("\nprykład:");
            System.out.println("java -jar orthography-converter.jar KA krynica.txt ŁT vynik.txt\n");
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
            String outputPath) throws IOException {
        Optional<Orthography> from = Orthography.fromCode(inputStyle);
        Optional<Orthography> to = Orthography.fromCode(outputStyle);
        if (from.isEmpty()) {
            System.out.println("Niapravilny styl uvachodnaha fajła.");
        } else if (to.isEmpty()) {
            System.out.println("Niapravilny styl vychodnaha fajła.");
        } else if (from.get() == to.get()) {
            System.out.println("Pravapis uvachodnaha i vychodnaha fajła adnolkavy.");
        } else {
            convertToFile(inputPath, outputPath, Converters.between(from.get(), to.get()));
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

    static void convertToFile(String inputPath, String outputPath, Converter converter) throws IOException {
        writeToFile(converter.convert(readFile(inputPath)), outputPath);
    }
}
