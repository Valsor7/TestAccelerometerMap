package com.boost.testaccelerometermap.presentation.utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by yaroslav on 07.06.17.
 */

public class TimeUtils {

    public static long getResetedDayInMillis(){
        GregorianCalendar cal = (GregorianCalendar) Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, cal.getActualMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE,      cal.getActualMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND,      cal.getActualMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getActualMinimum(Calendar.MILLISECOND));
        return cal.getTimeInMillis();
    }

    public static String getTimeFromMillis(long millis){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        date.setTime(millis);
        return format.format(date);
    }
}
