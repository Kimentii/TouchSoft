package main;

import java.util.Calendar;

public class TimeEntry implements Comparable<TimeEntry> {
    private Calendar time;
    private boolean start;

    public TimeEntry(Calendar time, boolean isStart) {
        this.time = time;
        this.start = isStart;
    }

    @Override
    public int compareTo(TimeEntry o) {
        if (time.compareTo(o.getTime()) != 0) {
            return time.compareTo(o.getTime());
        }
        if (start && o.isStart()) {
            return 0;
        }
        if (start) {
            return -1;
        }
        if (o.isStart()) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE))
                + " " + isStart();
    }

    public Calendar getTime() {
        return time;
    }

    public boolean isStart() {
        return start;
    }
}