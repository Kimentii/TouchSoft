package tests;

import main.Main;
import main.TimeEntry;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;


public class MainTest {

    @Test
    public void getRecordsFromFileTestEmptyFile() throws FileNotFoundException {
        ArrayList<String> records = Main.getRecordsFromFile("empty_file.txt");
        Assert.assertEquals(records.size(), 0);
    }

    @Test
    public void getRecordsFromFileTextFileWithEmptyStrings() throws FileNotFoundException {
        ArrayList<String> records = Main.getRecordsFromFile("file_with_empty_strings.txt");
        Assert.assertEquals(records.size(), 0);
    }

    @Test(expected = ParseException.class)
    public void parseAndSortRecordsTestThrowParseException() throws ParseException {
        ArrayList<String> records = new ArrayList<>();
        records.add("8.22 23:22");
        Main.parseAndSortRecords(records);
    }

    @Test
    public void countMaxNumberOfPeopleTestOneComeSecondGoOut() {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.HOUR_OF_DAY, 10);
        calendar1.set(Calendar.MINUTE, 30);
        TimeEntry timeEntry1 = new TimeEntry(calendar1, true);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.HOUR_OF_DAY, 10);
        calendar2.set(Calendar.MINUTE, 30);
        TimeEntry timeEntry2 = new TimeEntry(calendar2, false);

        ArrayList<TimeEntry> timeEntries = new ArrayList<>();
        timeEntries.add(timeEntry1);
        timeEntries.add(timeEntry2);

        Assert.assertEquals(Main.countMaxNumberOfPeople(timeEntries), 1);
    }
}