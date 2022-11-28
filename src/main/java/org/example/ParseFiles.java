package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.example.Constants.*;

public class ParseFiles {

    AbbreviationsDTO dto = new AbbreviationsDTO();

    public AbbreviationsDTO parse(List<String> startArray, List<String> endArray, List<String> abbreviationsArray) throws ParseException {

        for (int i = 0; i < abbreviationsArray.size(); i++) { // раскидываем файловые значения по мапам
            String[] abbArray = abbreviationsArray.get(i).split(DOWN_SPLIT); // 3 части, что заебись разложется по мапам
            dto.getAbbreviationNameMap().put(abbArray[0],abbArray[1]); // abb, name
            dto.getAbbreviationCarMap().put(abbArray[0], abbArray[2]); // abb, car
            dto.getStartMap().put(parseString(startArray, i), parseDate(startArray, i)); // abb, start time
            dto.getEndMap().put(parseString(endArray, i), parseDate(endArray, i)); // abb, end time
        }
        return dto;
    }

    public String parseString(List<String> array, int i) {
        StringBuilder abb = new StringBuilder(array.get(i));
        abb.delete(3, array.get(i).length());
        return abb.toString();
    }
        public Date parseDate(List<String> array, int i) throws ParseException {
        StringBuilder date = new StringBuilder(array.get(i));
        date.delete(0, 3);
        Date time = new SimpleDateFormat("y-MM-d_H:m:s.S").parse(date.toString());
            return time;
    }
}