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

class TimerTest {

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
        List<String> startArray1 = Files.readAllLines(Path.of("C:\\Users\\ASUS\\OneDrive\\Рабочий стол\\java_projects\\Top15\\src\\test\\resources\\Start_Test_1"));
        List<String> endArray1 = Files.readAllLines(Path.of("C:\\Users\\ASUS\\OneDrive\\Рабочий стол\\java_projects\\Top15\\src\\test\\resources\\End_Test_1"));
        List<String> abbreviationsArray1 = Files.readAllLines(Path.of("C:\\Users\\ASUS\\OneDrive\\Рабочий стол\\java_projects\\Top15\\src\\test\\resources\\Abbreviation_Test_1"));
        String expected1 = "null|null|null|null|null|";

        List<String> startArray2 = Files.readAllLines(Path.of("C:\\Users\\ASUS\\OneDrive\\Рабочий стол\\java_projects\\Top15\\src\\test\\resources\\Start_Test_2"));
        List<String> endArray2 = Files.readAllLines(Path.of("C:\\Users\\ASUS\\OneDrive\\Рабочий стол\\java_projects\\Top15\\src\\test\\resources\\End_Test_2"));
        List<String> abbreviationsArray2 = Files.readAllLines(Path.of("C:\\Users\\ASUS\\OneDrive\\Рабочий стол\\java_projects\\Top15\\src\\test\\resources\\Abbreviation_Test_2"));
        String expected2 = "72657|72950|72848|73028|72941|";

        List<String> startArray3 = Files.readAllLines(Path.of("C:\\Users\\ASUS\\OneDrive\\Рабочий стол\\java_projects\\Top15\\src\\test\\resources\\Start_Test_3"));
        List<String> endArray3 = Files.readAllLines(Path.of("C:\\Users\\ASUS\\OneDrive\\Рабочий стол\\java_projects\\Top15\\src\\test\\resources\\End_Test_3"));
        List<String> abbreviationsArray3 = Files.readAllLines(Path.of("C:\\Users\\ASUS\\OneDrive\\Рабочий стол\\java_projects\\Top15\\src\\test\\resources\\Abbreviation_Test_3"));
        String expected3 = "null|null|null|null|73179|";

        return Stream.of(
            Arguments.of(startArray1, endArray1, abbreviationsArray1, expected1),
            Arguments.of(startArray2, endArray2, abbreviationsArray2, expected2),
            Arguments.of(startArray3, endArray3, abbreviationsArray3, expected3)
        );
    }
}