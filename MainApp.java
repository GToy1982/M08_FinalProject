package com.finalproject;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainApp {
    private static final Map<String, String> timeZoneMappings = new HashMap<>();

    static {
        timeZoneMappings.put("GMT", "GMT");
        timeZoneMappings.put("EST", "America/New_York");
        timeZoneMappings.put("PST", "America/Los_Angeles");
    }

    private String currentTimeZone;
    private long localTimeMillis; // Use long to store time in milliseconds
    private String selectedLocation;

    public MainApp(String currentTimeZone, long localTimeMillis, String selectedLocation) {
        this.currentTimeZone = currentTimeZone;
        this.localTimeMillis = localTimeMillis;
        this.selectedLocation = selectedLocation;
    }

    public String getCurrentTimeZone() {
        return currentTimeZone;
    }

    public long getLocalTimeMillis() {
        return localTimeMillis;
    }

    public String getSelectedLocation() {
        return selectedLocation;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select your current time zone (GMT, EST, PST):");
        String currentTimeZoneInput = scanner.nextLine().toUpperCase();
        String currentTimeZone = timeZoneMappings.getOrDefault(currentTimeZoneInput, "GMT");

        System.out.println("Enter your local time in milliseconds since epoch:");
        long localTimeMillis = Long.parseLong(scanner.nextLine());

        System.out.println("Select the location you want to know the time for (GMT, EST, PST):");
        String selectedLocationInput = scanner.nextLine().toUpperCase();
        String selectedLocation = timeZoneMappings.getOrDefault(selectedLocationInput, "GMT");

        MainApp mainApp = new MainApp(currentTimeZone, localTimeMillis, selectedLocation);
        TimeZoneConverter timeZoneConverter = new TimeZoneConverter();
        String convertedTime = timeZoneConverter.convertTime(mainApp);
        System.out.println("Converted time: " + convertedTime);

        scanner.close();
    }
}
