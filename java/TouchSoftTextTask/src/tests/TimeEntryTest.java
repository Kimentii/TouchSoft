package tests;

import main.TimeEntry;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;

public class TimeEntryTest {

    @Test
    public void compareToTestTimeEquals() {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.HOUR_OF_DAY, 12);
        calendar1.set(Calendar.MINUTE, 12);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.HOUR_OF_DAY, 12);
        calendar2.set(Calendar.MINUTE, 12);

        TimeEntry timeEntry1 = new TimeEntry(calendar1, true);
        TimeEntry timeEntry2 = new TimeEntry(calendar2, false);

        Assert.assertTrue(timeEntry1.compareTo(timeEntry2) < 0);
    }

    @Test
    public void compareToTestFirstBigger() {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.HOUR_OF_DAY, 12);
        calendar1.set(Calendar.MINUTE, 30);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.HOUR_OF_DAY, 8);
        calendar2.set(Calendar.MINUTE, 9);

        TimeEntry timeEntry1 = new TimeEntry(calendar1, true);
        TimeEntry timeEntry2 = new TimeEntry(calendar2, false);

        Assert.assertTrue(timeEntry1.compareTo(timeEntry2) > 0);
    }

    @Test
    public void compareToTestSecondBigger() {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.HOUR_OF_DAY, 8);
        calendar1.set(Calendar.MINUTE, 15);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.HOUR_OF_DAY, 12);
        calendar2.set(Calendar.MINUTE, 30);

        TimeEntry timeEntry1 = new TimeEntry(calendar1, true);
        TimeEntry timeEntry2 = new TimeEntry(calendar2, false);

        Assert.assertTrue(timeEntry1.compareTo(timeEntry2) < 0);
    }

    @Test
    public void compareToTestAbsolutelyEquals() {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.HOUR_OF_DAY, 8);
        calendar1.set(Calendar.MINUTE, 8);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.HOUR_OF_DAY, 8);
        calendar2.set(Calendar.MINUTE, 8);

        TimeEntry timeEntry1 = new TimeEntry(calendar1, true);
        TimeEntry timeEntry2 = new TimeEntry(calendar2, true);

        Assert.assertEquals(0, timeEntry1.compareTo(timeEntry2));
    }
}
