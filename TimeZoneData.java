package com.finalproject;

import java.util.HashMap;
import java.util.Map;

public class TimeZoneData {
    private Map<String, String> timeZoneMap;

    public TimeZoneData() {
        timeZoneMap = new HashMap<>();
        // Populate the map with sample time zone data for demonstration
        timeZoneMap.put("GMT", "Greenwich Mean Time");
        timeZoneMap.put("EST", "Eastern Standard Time");
        timeZoneMap.put("PST", "Pacific Standard Time");
    
    }

    public String getTimeZoneName(String timeZoneAbbreviation) {
        return timeZoneMap.get(timeZoneAbbreviation);
    }
}
