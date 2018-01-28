package com.kamiture.kamui.stopwatch;

import android.content.SharedPreferences;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by lifeistech on 2018/01/28.
 */

public class Record {
    long elapsedTime;
    Date date;

    public Record(long elapsedTime, Date date) {
        this.elapsedTime = elapsedTime;
        this.date = date;
    }
}
