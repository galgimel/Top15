package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> startArray = Files.readAllLines(Path.of("C:\\Users\\ASUS\\OneDrive\\Рабочий стол\\java_projects\\Top15\\src\\main\\resources\\Start"));
        List<String> endArray = Files.readAllLines(Path.of("C:\\Users\\ASUS\\OneDrive\\Рабочий стол\\java_projects\\Top15\\src\\main\\resources\\End"));
        List<String> abbreviationsArray = Files.readAllLines(Path.of("C:\\Users\\ASUS\\OneDrive\\Рабочий стол\\java_projects\\Top15\\src\\main\\resources\\Abbreviations"));

        ParseFiles parser = new ParseFiles();
        Timer timer = new Timer();
        Formatter formatter = new Formatter(timer);
        AbbreviationsDTO dto = timer.timer(parser.parse(startArray, endArray, abbreviationsArray));

        for (String abbreviation : dto.getAbbreviationNameMap().keySet()) {
            String name = dto.getAbbreviationNameMap().get(abbreviation);
            String car = dto.getAbbreviationCarMap().get(abbreviation);
            long time = dto.getResultMap().get(abbreviation);
            dto.getTopRacers().add(new Racer(name, car, time));
        }
        dto.getTopRacers().sort(Racer::compareTo);

        System.out.println(formatter.format(startArray, endArray, abbreviationsArray, dto.getTopRacers()));
    }
}