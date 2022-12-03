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

class FormatterTest {

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
            try {
                long time = dto.getResultMap().get(abbreviation);
                dto.getTopRacers().add(new Racer(name, car, time));
            } catch (NullPointerException e) {}
        }
        dto.getTopRacers().sort(Racer::compareTo);

        String actual = formatter.format(startArray, endArray, abbreviationsArray, dto.getTopRacers());
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> expectedAnswer() throws IOException {
        List<String> startArray1 = Files.readAllLines(Path.of("C:\\Users\\ASUS\\OneDrive\\Рабочий стол\\java_projects\\Top15\\src\\test\\resources\\Start_Test_1"));
        List<String> endArray1 = Files.readAllLines(Path.of("C:\\Users\\ASUS\\OneDrive\\Рабочий стол\\java_projects\\Top15\\src\\test\\resources\\End_Test_1"));
        List<String> abbreviationsArray1 = Files.readAllLines(Path.of("C:\\Users\\ASUS\\OneDrive\\Рабочий стол\\java_projects\\Top15\\src\\test\\resources\\Abbreviation_Test_1"));
        String expected1 = "";

        List<String> startArray2 = Files.readAllLines(Path.of("C:\\Users\\ASUS\\OneDrive\\Рабочий стол\\java_projects\\Top15\\src\\test\\resources\\Start_Test_2"));
        List<String> endArray2 = Files.readAllLines(Path.of("C:\\Users\\ASUS\\OneDrive\\Рабочий стол\\java_projects\\Top15\\src\\test\\resources\\End_Test_2"));
        List<String> abbreviationsArray2 = Files.readAllLines(Path.of("C:\\Users\\ASUS\\OneDrive\\Рабочий стол\\java_projects\\Top15\\src\\test\\resources\\Abbreviation_Test_2"));
        String expected2 =
            "1.Fernando Alonso     | MCLAREN RENAULT                | 01:12.657\n" +
            "2.Sergio Perez        | FORCE INDIA MERCEDES           | 01:12.848\n" +
            "3.Pierre Gasly        | SCUDERIA TORO ROSSO HONDA      | 01:12.941\n" +
            "4.Carlos Sainz        | RENAULT                        | 01:12.950\n" +
            "5.Esteban Ocon        | FORCE INDIA MERCEDES           | 01:13.28\n";

        List<String> startArray3 = Files.readAllLines(Path.of("C:\\Users\\ASUS\\OneDrive\\Рабочий стол\\java_projects\\Top15\\src\\test\\resources\\Start_Test_3"));
        List<String> endArray3 = Files.readAllLines(Path.of("C:\\Users\\ASUS\\OneDrive\\Рабочий стол\\java_projects\\Top15\\src\\test\\resources\\End_Test_3"));
        List<String> abbreviationsArray3 = Files.readAllLines(Path.of("C:\\Users\\ASUS\\OneDrive\\Рабочий стол\\java_projects\\Top15\\src\\test\\resources\\Abbreviation_Test_3"));
        String expected3 = "1.Brendon Hartley     | SCUDERIA TORO ROSSO HONDA      | 01:13.179\n";

        return Stream.of(
            Arguments.of(startArray1, endArray1, abbreviationsArray1, expected1),
            Arguments.of(startArray2, endArray2, abbreviationsArray2, expected2),
            Arguments.of(startArray3, endArray3, abbreviationsArray3, expected3)
        );
    }
}