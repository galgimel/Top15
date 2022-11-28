package org.example;

import java.text.ParseException;
import java.util.List;


public class Timer {
    private ParseFiles parser;

    public Timer(ParseFiles parser) {
        this.parser = parser;
    }

    public AbbreviationsDTO timer(List<String> startArray, List<String> endArray, List<String> abbreviationsArray) throws ParseException {
        parser.parse(startArray, endArray, abbreviationsArray);
        AbbreviationsDTO dto = parser.parse(startArray, endArray, abbreviationsArray);

        for (String key : dto.getEndMap().keySet()) {
            if (dto.getStartMap().containsKey(key)) {
                long startMilli = dto.getStartMap().get(key).getTime();
                long endMilli = dto.getEndMap().get(key).getTime();
                long resultMilli = endMilli - startMilli;
                dto.getResultMap().put(key, resultMilli);
            }
        }
        return dto;
    }
}