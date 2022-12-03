package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.example.Constants.*;

public class ParseFiles {

    public AbbreviationsDTO parse(
        List<String> startArray,
        List<String> endArray,
        List<String> abbreviationsArray
    ) {
        AbbreviationsDTO dto = new AbbreviationsDTO();

        for (int i = 0; i < abbreviationsArray.size(); i++) {
            String[] splitAbbreviationFile = abbreviationsArray.get(i).split(DOWN_SPLIT);
            dto.getAbbreviationNameMap().put(splitAbbreviationFile[0], splitAbbreviationFile[1]);
            dto.getAbbreviationCarMap().put(splitAbbreviationFile[0], splitAbbreviationFile[2]);
            dto.getStartMap().put(getAbbreviations(startArray, i), getDate(startArray, i));
            dto.getEndMap().put(getAbbreviations(endArray, i), getDate(endArray, i));
        }
        return dto;
    }

    public String getAbbreviations(List<String> fileArray, int iCount) {
        StringBuilder getAbbreviation = new StringBuilder(fileArray.get(iCount));
        getAbbreviation.delete(ABBREVIATION_LENGTH, fileArray.get(iCount).length());
        return getAbbreviation.toString();
    }

    public Date getDate(List<String> array, int i) {
        StringBuilder parsedDateFile = new StringBuilder(array.get(i));
        parsedDateFile.delete(0, ABBREVIATION_LENGTH);
        try {
            return new SimpleDateFormat("y-MM-d_H:m:s.S").parse(parsedDateFile.toString());
        } catch (ParseException e) {
            System.out.println("ParseException, Parse files, parseDate method");
            throw new IllegalStateException(e);
        }
    }
}