package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.example.Constants.*;

public class Formatter {
    private Timer timer;

    public Formatter(Timer timer) {
        this.timer = timer;
    }

    public String format(List<String> startArray, List<String> endArray, List<String> abbreviationsArray, List<Racer> racers) throws ParseException {
        timer.timer(startArray, endArray, abbreviationsArray);
        AbbreviationsDTO dto = timer.timer(startArray, endArray, abbreviationsArray);

        List<String> names = new ArrayList<>(dto.getAbbreviationNameMap().values());
        List<String> cars = new ArrayList<>(dto.getAbbreviationCarMap().values());

        StringBuilder result = new StringBuilder();
        int maxNameLength = maxLineLength(names) + 5;
        int maxCarNameLength = maxLineLength(cars) + 5;
        int maxTimeLength = 9;
        int maxLength = maxNameLength + maxCarNameLength + maxTimeLength + 6; // 15 string
        int topCount = 1;


        for (int i = 0; i < racers.size(); i++) {
            String top = topCount + "";
            String name = dto.getTopRacers().get(i).getName();
            String carName = dto.getTopRacers().get(i).getCar();
            result.append(topCount++)
                .append(DOT)
                .append(name)//
                .append(space(maxNameLength - name.length() - top.length(), EMPTY_SPACE))//
                .append(SPLIT)
                .append(EMPTY_SPACE)
                .append(carName)//
                .append(space(maxCarNameLength - carName.length(), EMPTY_SPACE))//
                .append(SPLIT)
                .append(EMPTY_SPACE)
                .append(new SimpleDateFormat("mm:ss.S").format(dto.getTopRacers().get(i).date))
                .append("\n");
            if (topCount == 16)
                result.append(space(maxLength, MINUS))
                    .append("\n");
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