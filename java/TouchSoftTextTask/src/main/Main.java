package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    private static final String FILE_NAME = "input.txt";

    public static ArrayList<String> getRecordsFromFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        ArrayList<String> result = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (!line.isEmpty()) {
                result.add(line);
            }
        }
        return result;
    }


    public static ArrayList<TimeEntry> parseAndSortRecords(ArrayList<String> records) throws ParseException {
        ArrayList<TimeEntry> allEntries = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
        for (String r : records) {
            String[] period = r.split(" ");

            Calendar startTime = Calendar.getInstance();
            startTime.setTime(dateFormat.parse(period[0]));
            allEntries.add(new TimeEntry(startTime, true));

            Calendar endTime = Calendar.getInstance();
            endTime.setTime(dateFormat.parse(period[1]));
            allEntries.add(new TimeEntry(endTime, false));

        }
        Collections.sort(allEntries);
        return allEntries;
    }

    public static int countMaxNumberOfPeople(ArrayList<TimeEntry> entries) {
        int maxNum = 0;
        int temp = 0;
        for (TimeEntry entry : entries) {
            if (entry.isStart()) {
                temp++;
            } else {
                temp--;
            }
            if (temp > maxNum) {
                maxNum = temp;
            }
        }
        return maxNum;
    }

    public static void main(String[] args) {
        try {
            ArrayList<String> records = getRecordsFromFile(FILE_NAME);
            ArrayList<TimeEntry> timeEntries = parseAndSortRecords(records);
            System.out.println(countMaxNumberOfPeople(timeEntries));
        } catch (ParseException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}