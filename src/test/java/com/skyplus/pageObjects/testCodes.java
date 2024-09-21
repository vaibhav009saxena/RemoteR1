package com.skyplus.pageObjects;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
public class testCodes {

//    public boolean findResultsInGivenTimeRange(String timeRanges){
//        // Check if expected time lies within the given time ranges
//        boolean found = false;
//        for (String timeRange : timeRanges) {
//            try {
//                LocalTime start = LocalTime.parse(timeRange, DateTimeFormatter.ofPattern("HH:mm"));
//                LocalTime end = start.plusMinutes(59); // Assuming each range is 1 hour
//
//                if (expectedTime.isAfter(start) && expectedTime.isBefore(end)) {
//                    found = true;
//                    break;
//                }
//            } catch (DateTimeParseException e) {
//                System.out.println("Invalid time format: " + timeRange);
//            }
//        }
//        return found;
//    }
    public static void main(String[] args) {
        boolean found = false;

        while(found != true){

            // Get the current time in 24-hour format
            LocalTime currentTime = LocalTime.now();
            System.out.println("Current Time: " + currentTime.format(DateTimeFormatter.ofPattern("HH:mm")));

            // Check if current time is before 12:00 PM and add 12 hours if true
            LocalTime expectedTime = currentTime;
            if (currentTime.isBefore(LocalTime.NOON)) {
                expectedTime = currentTime.plusHours(12);
            }
            System.out.println("Expected Time: " + expectedTime.format(DateTimeFormatter.ofPattern("HH:mm")));

            //get the time range from the srp page
            List<String> timeRanges = new ArrayList<>();
            timeRanges.add("23:00");
            timeRanges.add("23:59");
            timeRanges.add("10:34");
            timeRanges.add("01:06");
            timeRanges.add("14:06");

            // Check if expected time lies within the given time ranges

            for (String timeRange : timeRanges) {
                try {
                    LocalTime start = LocalTime.parse(timeRange, DateTimeFormatter.ofPattern("HH:mm"));
                    LocalTime end = start.plusMinutes(59);

                    if (expectedTime.isAfter(start) && expectedTime.isBefore(end)) {
                        found = true;
                        break;
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid time format: " + timeRange);
                }
            }

            // Print the result
            if (found) {
                System.out.println("Expected time found in the time ranges.");
            } else {
                System.out.println("change the date as no result found beyond 12 hrs");

            }


        }

    }
}
