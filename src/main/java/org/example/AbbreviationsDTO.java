package org.example;

import java.util.*;

public class AbbreviationsDTO {
    /**
     * startMap - abb , start time
     * endMap - abb, end time
     * resultMap - abb, result time in milli for top comparable
     * abbreviationNameMap - abb, name
     * abbreviationCarMap - abb, car name
     */
    private Map<String, Date> startMap = new HashMap<>();
    private Map<String, Date> endMap = new HashMap<>();
    private Map<String, Long> resultMap = new HashMap<>();
    private Map<String, String> abbreviationNameMap = new HashMap<>();
    private Map<String, String> abbreviationCarMap = new HashMap<>();

    private List<Racer> topRacers = new ArrayList<>();



    public Map<String, Date> getStartMap() {
        return startMap;
    }

    public void setStartMap(Map<String, Date> startMap) {
        this.startMap = startMap;
    }

    public Map<String, Date> getEndMap() {
        return endMap;
    }

    public void setEndMap(Map<String, Date> endMap) {
        this.endMap = endMap;
    }

    public Map<String, Long> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, Long> resultMap) {
        this.resultMap = resultMap;
    }

    public Map<String, String> getAbbreviationNameMap() {
        return abbreviationNameMap;
    }

    public void setAbbreviationNameMap(Map<String, String> abbreviationNameMap) {
        this.abbreviationNameMap = abbreviationNameMap;
    }
    public Map<String, String> getAbbreviationCarMap() {
        return abbreviationCarMap;
    }

    public void setAbbreviationMap(Map<String, String> abbreviationCarMap) {
        this.abbreviationCarMap = abbreviationCarMap;
    }

    public List<Racer> getTopRacers() { return topRacers; }

    public void setTopRacers(List<Racer> topRacers) { this.topRacers = topRacers; }
}