package com.boost.testaccelerometermap.presentation.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;

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
}
