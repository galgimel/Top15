package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static org.example.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class timerTimer {

    @ParameterizedTest
    @MethodSource("expectedAnswer")
    void timer(
        List<String> startArray,
        List<String> endArray,
        List<String> abbreviationsArray,
        String expected
    ) {
        ParseFiles parser = new ParseFiles();
        Timer timer = new Timer();
        AbbreviationsDTO parsedDTO = parser.parse(startArray, endArray, abbreviationsArray);
        AbbreviationsDTO dto = timer.timer(parsedDTO);
        StringBuilder actual = new StringBuilder();
        for (String abb : dto.getAbbreviationNameMap().keySet()) {
            actual.append(dto.getResultMap().get(abb))
                .append(SPLIT);
        }
        assertEquals(expected, actual.toString());
    }

    private static Stream<Arguments> expectedAnswer() throws IOException {

        List<String> startArray2 = Files.readAllLines(Path.of("src\\test\\resources\\Start_Test"));
        List<String> endArray2 = Files.readAllLines(Path.of("src\\test\\resources\\End_Test"));
        List<String> abbreviationsArray2 = Files.readAllLines(Path.of("src\\test\\resources\\Abbreviation_Test"));
        String expected2 = "72657|72950|72848|73028|72941|";

        return Stream.of(
            Arguments.of(startArray2, endArray2, abbreviationsArray2, expected2)
        );
    }
}