package org.example;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.example.Constants.*;

public class Formatter {
    private Timer timer;

    public Formatter(Timer timer) {
        this.timer = timer;
    }

    public String format(
        List<String> startArray,
        List<String> endArray,
        List<String> abbreviationsArray,
        List<Racer> racers
    ) {

        timer.timer(startArray, endArray, abbreviationsArray);
        AbbreviationsDTO dto = timer.timer(startArray, endArray, abbreviationsArray);

        List<String> names = new ArrayList<>(dto.getAbbreviationNameMap().values());
        List<String> cars = new ArrayList<>(dto.getAbbreviationCarMap().values());

        StringBuilder result = new StringBuilder();
        int topCount = 1;
        String top = topCount + "";
        SimpleDateFormat timeFormat = new SimpleDateFormat(FORMAT_TIME);
        int maxNameLength = maxLineLength(names) + FREE_SPACE;
        int maxCarNameLength = maxLineLength(cars) + FREE_SPACE;
        int maxTimeLength = TIME_LENGTH;
        int maxLength = maxNameLength +
            maxCarNameLength +
            maxTimeLength +
            top.length() +
            ADD_LINE_SPACE;

        for (int i = 0; i < racers.size(); i++) {

            String name = dto.getTopRacers().get(i).getName();
            String carName = dto.getTopRacers().get(i).getCar();
            result.append(topCount++)
                .append(DOT)
                .append(name)
                .append(space(maxNameLength - name.length() - top.length(), EMPTY_SPACE))
                .append(SPLIT)
                .append(EMPTY_SPACE)
                .append(carName)
                .append(space(maxCarNameLength - carName.length(), EMPTY_SPACE))
                .append(SPLIT)
                .append(EMPTY_SPACE)
                .append(timeFormat.format(dto.getTopRacers().get(i).date))
                .append("\n");
            if (topCount == 16) {
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