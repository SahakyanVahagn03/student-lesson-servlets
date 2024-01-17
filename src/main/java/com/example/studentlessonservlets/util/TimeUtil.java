package com.example.studentlessonservlets.util;

import lombok.experimental.UtilityClass;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@UtilityClass
public class TimeUtil {
    private final SimpleDateFormat SIMPLE_TIME_FORMAT = new SimpleDateFormat("HH:mm");

    public Time stringToTime(String timeStr) throws ParseException {
        long timeInMilliseconds = SIMPLE_TIME_FORMAT.parse(timeStr).getTime();
        return new Time(timeInMilliseconds);
    }

    public String timeToString(Time time) {
        return SIMPLE_TIME_FORMAT.format(time);
    }
}
