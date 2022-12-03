package org.example;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.example.Constants.*;

public class Formatter {
    private Timer timer;
    private ParseFiles parser = new ParseFiles();

    public Formatter(Timer timer) {
        this.timer = timer;
    }

    public String format(
        List<String> startArray,
        List<String> endArray,
        List<String> abbreviationsArray,
        List<Racer> racers
    ) {

        AbbreviationsDTO dto = timer.timer(parser.parse(startArray, endArray, abbreviationsArray));

        List<String> names = new ArrayList<>(dto.getAbbreviationNameMap().values());
        List<String> cars = new ArrayList<>(dto.getAbbreviationCarMap().values());

        StringBuilder result = new StringBuilder();
        int topCount = 1;
        SimpleDateFormat timeFormat = new SimpleDateFormat(FORMAT_TIME);
        int maxNameLength = maxLineLength(names) + FREE_SPACE;
        int maxCarNameLength = maxLineLength(cars) + FREE_SPACE;
        int maxLength = TOP_STRING.length() +
            maxNameLength +
            SPLIT.length() +
            EMPTY_SPACE.length() +
            maxCarNameLength +
            SPLIT.length() +
            EMPTY_SPACE.length() +
            TIME_LENGTH;

        for (int i = 0; i < racers.size(); i++) {
            String topCountString = String.valueOf(topCount);
            String name = racers.get(i).getName();
            String carName = racers.get(i).getCar();
            result.append(topCount++)
                .append(DOT)
                .append(name)
                .append(space(maxNameLength - name.length() - topCountString.length(), EMPTY_SPACE_CHAR))
                .append(SPLIT)
                .append(EMPTY_SPACE_CHAR)
                .append(carName)
                .append(space(maxCarNameLength - carName.length(), EMPTY_SPACE_CHAR))
                .append(SPLIT)
                .append(EMPTY_SPACE_CHAR)
                .append(timeFormat.format(racers.get(i).date))
                .append("\n");
            if (topCount == TOP) {
                result.append(space(maxLength, MINUS))
                    .append("\n");
            }
        }
        return result.toString();
    }

    private int maxLineLength(List<String> array) {
        int max = 0;
        for (String fileString : array) {
            if (fileString.length() > max) {
                max = fileString.length();
            }
        }
        return max;
    }

    public static String space(int length, char c) {
        return String.valueOf(c).repeat(Math.max(0, length + 1));
    }
}