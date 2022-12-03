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

class ParseFilesTest {

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

            try {
                actual.append("startMap - " + abbreviation + ", " + dto.getStartMap().get(abbreviation) + "\n" +
                    "endMap - " + abbreviation + ", " + dto.getEndMap().get(abbreviation) + "\n" +
                    "abbreviationNameMap -" + abbreviation + ", " + dto.getAbbreviationNameMap().get(abbreviation) + "\n" +
                    "abbreviationCarMap -" + abbreviation + ", " + dto.getAbbreviationCarMap().get(abbreviation) + "\n\n"
                );
            } catch (NullPointerException e) {
            }
        }
        assertEquals(expected, actual.toString());
    }

    private static Stream<Arguments> expectedAnswer() throws IOException {
        List<String> startArray1 = Files.readAllLines(Path.of("C:\\Users\\ASUS\\OneDrive\\Рабочий стол\\java_projects\\Top15\\src\\test\\resources\\Start_Test_1"));
        List<String> endArray1 = Files.readAllLines(Path.of("C:\\Users\\ASUS\\OneDrive\\Рабочий стол\\java_projects\\Top15\\src\\test\\resources\\End_Test_1"));
        List<String> abbreviationsArray1 = Files.readAllLines(Path.of("C:\\Users\\ASUS\\OneDrive\\Рабочий стол\\java_projects\\Top15\\src\\test\\resources\\Abbreviation_Test_1"));
        String expected1 = "startMap - LHM, null\n" +
            "endMap - LHM, Thu May 24 12:19:32 MSK 2018\n" +
            "abbreviationNameMap -LHM, Lewis Hamilton\n" +
            "abbreviationCarMap -LHM, MERCEDES\n" +
            "\n" +
            "startMap - VBM, null\n" +
            "endMap - VBM, null\n" +
            "abbreviationNameMap -VBM, Valtteri Bottas\n" +
            "abbreviationCarMap -VBM, MERCEDES\n" +
            "\n" +
            "startMap - SVF, null\n" +
            "endMap - SVF, null\n" +
            "abbreviationNameMap -SVF, Sebastian Vettel\n" +
            "abbreviationCarMap -SVF, FERRARI\n" +
            "\n" +
            "startMap - DRR, null\n" +
            "endMap - DRR, null\n" +
            "abbreviationNameMap -DRR, Daniel Ricciardo\n" +
            "abbreviationCarMap -DRR, RED BULL RACING TAG HEUER\n" +
            "\n" +
            "startMap - KRF, null\n" +
            "endMap - KRF, null\n" +
            "abbreviationNameMap -KRF, Kimi Raikkonen\n" +
            "abbreviationCarMap -KRF, FERRARI\n" +
            "\n";

        List<String> startArray2 = Files.readAllLines(Path.of("C:\\Users\\ASUS\\OneDrive\\Рабочий стол\\java_projects\\Top15\\src\\test\\resources\\Start_Test_2"));
        List<String> endArray2 = Files.readAllLines(Path.of("C:\\Users\\ASUS\\OneDrive\\Рабочий стол\\java_projects\\Top15\\src\\test\\resources\\End_Test_2"));
        List<String> abbreviationsArray2 = Files.readAllLines(Path.of("C:\\Users\\ASUS\\OneDrive\\Рабочий стол\\java_projects\\Top15\\src\\test\\resources\\Abbreviation_Test_2"));
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

        List<String> startArray3 = Files.readAllLines(Path.of("C:\\Users\\ASUS\\OneDrive\\Рабочий стол\\java_projects\\Top15\\src\\test\\resources\\Start_Test_3"));
        List<String> endArray3 = Files.readAllLines(Path.of("C:\\Users\\ASUS\\OneDrive\\Рабочий стол\\java_projects\\Top15\\src\\test\\resources\\End_Test_3"));
        List<String> abbreviationsArray3 = Files.readAllLines(Path.of("C:\\Users\\ASUS\\OneDrive\\Рабочий стол\\java_projects\\Top15\\src\\test\\resources\\Abbreviation_Test_3"));
        String expected3 = "startMap - LSW, Thu May 24 12:06:13 MSK 2018\n" +
            "endMap - LSW, null\n" +
            "abbreviationNameMap -LSW, Lance Stroll\n" +
            "abbreviationCarMap -LSW, WILLIAMS MERCEDES\n" +
            "\n" +
            "startMap - RGH, Thu May 24 12:05:14 MSK 2018\n" +
            "endMap - RGH, null\n" +
            "abbreviationNameMap -RGH, Romain Grosjean\n" +
            "abbreviationCarMap -RGH, HAAS FERRARI\n" +
            "\n" +
            "startMap - MES, Thu May 24 12:04:45 MSK 2018\n" +
            "endMap - MES, null\n" +
            "abbreviationNameMap -MES, Marcus Ericsson\n" +
            "abbreviationCarMap -MES, SAUBER FERRARI\n" +
            "\n" +
            "startMap - CLS, null\n" +
            "endMap - CLS, null\n" +
            "abbreviationNameMap -CLS, Charles Leclerc\n" +
            "abbreviationCarMap -CLS, SAUBER FERRARI\n" +
            "\n" +
            "startMap - BHS, Thu May 24 12:14:51 MSK 2018\n" +
            "endMap - BHS, Thu May 24 12:16:05 MSK 2018\n" +
            "abbreviationNameMap -BHS, Brendon Hartley\n" +
            "abbreviationCarMap -BHS, SCUDERIA TORO ROSSO HONDA\n" +
            "\n";

        return Stream.of(
            Arguments.of(startArray1, endArray1, abbreviationsArray1, expected1),
            Arguments.of(startArray2, endArray2, abbreviationsArray2, expected2),
            Arguments.of(startArray3, endArray3, abbreviationsArray3, expected3)
        );
    }
}