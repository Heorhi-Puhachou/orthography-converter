## Project Improvements

### Quick Wins
- Make regex patterns static final (`Pattern.compile`) in `StringUtilCheck`.
- Convert mutable `pairs`/`pairsL` maps to immutable `static final` maps (e.g., `Map.ofEntries`).
- Replace `result = result + symbol` with `StringBuilder`.
- Use try‑with‑resources or `Files.writeString` for file I/O in `Main`.
- Add existence check for input file before reading.

### Architecture Enhancements
- Replace linear converter chain with a bidirectional graph to compute optimal conversion paths.
- Externalize conversion rules to JSON/YAML for data‑driven updates.
- Provide a programmatic API (`convert(String from, String to, String text)`) and support stdin/stdout for pipeline usage.
- Introduce a logging framework (slf4j/simple‑logger) instead of `System.out`.

### Performance & Safety
- Stream large files instead of `Files.readAllBytes` to reduce memory usage.
- Apply Unicode normalization (`Normalizer.normalize`) before processing.
- Cache compiled regexes and immutable maps to avoid per‑call allocations.

### Feature Additions
- Batch conversion of all `.txt` files in a directory (recursive).
- In‑memory conversion returning a `String` for library consumers.
- CLI argument validation with proper exit codes (consider picocli or Commons CLI).

### Rule Quality Improvements
- Integrate stress detection with a dictionary or annotation tool.
- Remove or wire unused `SofteningConsonants`/`DoubleSoftConsonants`.
- Implement longest‑match‑first replacement order instead of sequential `String.replace`.

### Testing Enhancements
- Increase unit test coverage for each rule class.
- Add round‑trip conversion tests.
- Property‑based tests for random strings and large inputs.
- Performance benchmarks for streaming vs whole‑file processing.
- Edge‑case tests: empty input, emojis, zero‑width chars, mixed scripts.

### Documentation & Tooling
- Add a conversion matrix diagram.
- Provide usage examples for each direction in the README.
- Translate README/comments to English.
- Add Javadoc to public APIs.
- Create a Maven profile for building a GraalVM native image.
