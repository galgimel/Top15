package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class testParser {

    @ParameterizedTest
    @MethodSource("expectedAnswer")
    void parse(List<String> startArray,
               List<String> endArray,
               List<String> abbreviationsArray,
               String expected
    ) {
        ParseFiles parser = new ParseFiles();
        AbbreviationsDTO dto = parser.parse(startArray, endArray, abbreviationsArray);
        StringBuilder actual = new StringBuilder();
        for (String abbreviation : dto.getAbbreviationNameMap().keySet()) {
            actual.append("startMap - " + abbreviation + ", " + dto.getStartMap().get(abbreviation) + "\n" +
                "endMap - " + abbreviation + ", " + dto.getEndMap().get(abbreviation) + "\n" +
                "abbreviationNameMap -" + abbreviation + ", " + dto.getAbbreviationNameMap().get(abbreviation) + "\n" +
                "abbreviationCarMap -" + abbreviation + ", " + dto.getAbbreviationCarMap().get(abbreviation) + "\n\n"
            );
        }
        assertEquals(expected, actual.toString());
    }

    private static Stream<Arguments> expectedAnswer() throws IOException {

        List<String> startArray2 = Files.readAllLines(Path.of("src\\test\\resources\\Start_Test"));
        List<String> endArray2 = Files.readAllLines(Path.of("src\\test\\resources\\End_Test"));
        List<String> abbreviationsArray2 = Files.readAllLines(Path.of("src\\test\\resources\\Abbreviation_Test"));
        String expected2 = "startMap - FAM, Thu May 24 12:13:04 MSK 2018\n" +
            "endMap - FAM, Thu May 24 12:14:17 MSK 2018\n" +
            "abbreviationNameMap -FAM, Fernando Alonso\n" +
            "abbreviationCarMap -FAM, MCLAREN RENAULT\n" +
            "\n" +
            "startMap - CSR, Thu May 24 12:03:15 MSK 2018\n" +
            "endMap - CSR, Thu May 24 12:04:28 MSK 2018\n" +
            "abbreviationNameMap -CSR, Carlos Sainz\n" +
            "abbreviationCarMap -CSR, RENAULT\n" +
            "\n" +
            "startMap - SPF, Thu May 24 12:12:01 MSK 2018\n" +
            "endMap - SPF, Thu May 24 12:13:13 MSK 2018\n" +
            "abbreviationNameMap -SPF, Sergio Perez\n" +
            "abbreviationCarMap -SPF, FORCE INDIA MERCEDES\n" +
            "\n" +
            "startMap - EOF, Thu May 24 12:17:58 MSK 2018\n" +
            "endMap - EOF, Thu May 24 12:19:11 MSK 2018\n" +
            "abbreviationNameMap -EOF, Esteban Ocon\n" +
            "abbreviationCarMap -EOF, FORCE INDIA MERCEDES\n" +
            "\n" +
            "startMap - PGS, Thu May 24 12:07:23 MSK 2018\n" +
            "endMap - PGS, Thu May 24 12:08:36 MSK 2018\n" +
            "abbreviationNameMap -PGS, Pierre Gasly\n" +
            "abbreviationCarMap -PGS, SCUDERIA TORO ROSSO HONDA\n" +
            "\n";

        return Stream.of(
            Arguments.of(startArray2, endArray2, abbreviationsArray2, expected2)
        );
    }
}