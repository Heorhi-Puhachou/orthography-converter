# Project Improvements

## Quick Wins

- **`isWordSymbol()` compiles a new `Pattern` on every call** — should be `static final`. Same with the `pairsL` HashMaps in `LTKKConverter`/`LAKAConverter` (immutable constant data)
- **String concatenation in loops** (`result = result + symbol`) — use `StringBuilder`
- **Resource leak** — `FileWriter` in `Main.java:58` should be try-with-resources
- **No file existence check** before reading input

## Architecture

- **Replace the linear chain** (`LT <-> KK <-> KA <-> LA`) with a **bidirectional graph** — this lets you pick the optimal path between any two orthographies instead of forcing a fixed route
- **Make the rule engine data-driven** — load template/start/end replacements from a JSON/YAML file instead of hardcoding them. This would let users add new rules without touching Java code

## Features

- **Streaming/chunked processing** for large files (currently `Files.readAllBytes` loads everything into memory)
- **Unicode normalization** (NFC/NFD) to handle precomposed vs decomposed characters consistently
- **In-memory API** — return `String` instead of writing to file, so it's usable as a library from other projects
- **Batch mode** — convert all `.txt` files in a directory recursively

## Rule Quality

- **Stress detection** for BNP rules (`ня`/`не`, `бяз`/`без`) is a hardcoded list of ~6 words — integrate with a stress annotation tool or dictionary
- **`SofteningConsonants` and `DoubleSoftConsonants`** appear unused — remove or wire them in
- **Template replacement order** is fragile — consider a longest-match-first approach instead of linear `String.replace()`

## Tests

- **Coverage for individual rule classes** (`Softeners`, `TemplateReplace`, etc.)
- **Round-trip tests** (KA -> KK -> LA should be reasonable)
- **Edge cases**: empty input, emojis, zero-width chars, combining marks, mixed scripts

## Documentation

- Add a **converter matrix diagram** showing all supported directions
- Add **examples** in README for each conversion direction
- Translate comments/README to English alongside Belarusian for broader accessibility
