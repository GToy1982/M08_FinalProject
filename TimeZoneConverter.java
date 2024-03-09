package com.finalproject;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimeZoneConverter {
    public String convertTime(MainApp app) {
        // Convert milliseconds since epoch to LocalDateTime
        Instant instant = Instant.ofEpochMilli(app.getLocalTimeMillis());
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.of(app.getCurrentTimeZone()));

        // Convert localDateTime to the target time zone
        ZoneId targetZone = ZoneId.of(app.getSelectedLocation());
        LocalDateTime convertedTime = localDateTime.atZone(ZoneId.of(app.getCurrentTimeZone()))
                .withZoneSameInstant(targetZone).toLocalDateTime();

        // Format the converted time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedConvertedTime = convertedTime.format(formatter);

        return formattedConvertedTime;
    }
}
