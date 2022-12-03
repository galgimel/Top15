package org.example;

public class Timer {

    public AbbreviationsDTO timer(AbbreviationsDTO parsedDTO) {

        for (String key : parsedDTO.getEndMap().keySet()) {
            if (parsedDTO.getStartMap().containsKey(key)) {
                long startMilli = parsedDTO.getStartMap().get(key).getTime();
                long endMilli = parsedDTO.getEndMap().get(key).getTime();
                long resultMilli = endMilli - startMilli;
                parsedDTO.getResultMap().put(key, resultMilli);
            }
        }
        return parsedDTO;
    }
}