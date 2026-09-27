import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Pravierka hatovaha jar-fajła.
 * <p>
 * Kožnaja papka ŭ src/test/resources/jar nazyvajecca "UVACHODNY-VYCHODNY" (napr. KA-ŁT)
 * i maje input.txt i expected.txt. Jar kanvertuje input.txt, vynik paraŭnoŭvajecca z expected.txt.
 */
public class JarIT {

    private static final Path JAR = Paths.get(System.getProperty("jar"));
    private static final Path CASES = Paths.get("src/test/resources/jar");
    private static final Path OUTPUT = Paths.get("target/jar-test");

    @TestFactory
    Stream<DynamicTest> convertsFile() throws IOException {
        List<Path> dirs;
        try (Stream<Path> list = Files.list(CASES)) {
            dirs = list.sorted().toList();
        }
        return dirs.stream().map(dir -> DynamicTest.dynamicTest(dir.getFileName().toString(), () -> check(dir)));
    }

    private static void check(Path dir) throws Exception {
        String[] codes = dir.getFileName().toString().split("-");
        Path output = OUTPUT.resolve(dir.getFileName()).resolve("output.txt");
        Files.createDirectories(output.getParent());
        Files.deleteIfExists(output);

        Process process = new ProcessBuilder(
                Paths.get(System.getProperty("java.home"), "bin", "java").toString(),
                "-jar", JAR.toString(),
                codes[0], dir.resolve("input.txt").toString(),
                codes[1], output.toString())
                .redirectErrorStream(true)
                .start();
        String log = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

        assertEquals(0, process.waitFor(), log);
        assertEquals(Files.readString(dir.resolve("expected.txt")), Files.readString(output), log);
    }
}
