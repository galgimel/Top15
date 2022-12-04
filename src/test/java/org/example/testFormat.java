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

class testFormat {

    @ParameterizedTest
    @MethodSource("expectedAnswer")
    void format(List<String> startArray,
                List<String> endArray,
                List<String> abbreviationsArray,
                String expected
    ) {
        ParseFiles parser = new ParseFiles();
        Timer timer = new Timer();
        Formatter formatter = new Formatter(timer);
        AbbreviationsDTO parsedDTO = parser.parse(startArray, endArray, abbreviationsArray);
        AbbreviationsDTO dto = timer.timer(parsedDTO);

        for (String abbreviation : dto.getAbbreviationNameMap().keySet()) {
            String name = dto.getAbbreviationNameMap().get(abbreviation);
            String car = dto.getAbbreviationCarMap().get(abbreviation);
            long time = dto.getResultMap().get(abbreviation);
            dto.getTopRacers().add(new Racer(name, car, time));
        }
        dto.getTopRacers().sort(Racer::compareTo);

        String actual = formatter.format(startArray, endArray, abbreviationsArray, dto.getTopRacers());
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> expectedAnswer() throws IOException {

        List<String> startArray2 = Files.readAllLines(Path.of("src\\test\\resources\\Start_Test"));
        List<String> endArray2 = Files.readAllLines(Path.of("src\\test\\resources\\End_Test"));
        List<String> abbreviationsArray2 = Files.readAllLines(Path.of("src\\test\\resources\\Abbreviation_Test"));
        String expected2 =
            "1.Fernando Alonso     | MCLAREN RENAULT                | 01:12.657\n" +
            "2.Sergio Perez        | FORCE INDIA MERCEDES           | 01:12.848\n" +
            "3.Pierre Gasly        | SCUDERIA TORO ROSSO HONDA      | 01:12.941\n" +
            "4.Carlos Sainz        | RENAULT                        | 01:12.950\n" +
            "5.Esteban Ocon        | FORCE INDIA MERCEDES           | 01:13.28\n";

        return Stream.of(
            Arguments.of(startArray2, endArray2, abbreviationsArray2, expected2
            )
        );
    }
}