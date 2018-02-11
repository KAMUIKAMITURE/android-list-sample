package com.kamiture.kamui.stopwatch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

import static com.kamiture.kamui.stopwatch.Utils.formatLongTime;
import static com.kamiture.kamui.stopwatch.Utils.loadList;
import static com.kamiture.kamui.stopwatch.Utils.saveList;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    ArrayAdapter adapter;
    private SharedPreferences data;

    CountUp countUp;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences data = getSharedPreferences("CountResult", MODE_PRIVATE);
        final SharedPreferences.Editor editor = data.edit();


        countUp = new CountUp(100);
        countUp.setOnFinishListener(new OnFinishListener() {
            @Override
            public void onFinish(long elapsedTime) {
                Log.d("lkne", String.valueOf(elapsedTime));


                Calendar rightNow = Calendar.getInstance();
                Date date = rightNow.getTime();
                // update saved list

                ArrayList<Record> recordList = loadList(MainActivity.this);
                recordList.add(new Record(elapsedTime, date));
                saveList(MainActivity.this, recordList);
//                editor.putLong("elpasedTime", elapsedTime);
//                editor.apply();

            }
        });
        countUp.setOnTickListener(new OnTickListener() {
            @Override
            public void onTick(long elapsedTime) {

                text.setText(formatLongTime(elapsedTime));

            }
        });
        text = findViewById(R.id.time);
    }
//    private Record loadAndParseRecord() {
//        // 保存してある long 型, Date 型 のデータをよみだしてくる TOFIX
//        data = getSharedPreferences("CountResult", MODE_PRIVATE);
//        long elapsedTime = data.getLong("CountLong", 0);
//
//        Calendar rightNow = Calendar.getInstance();
//        Date date = rightNow.getTime();
//
//        return new Record(elapsedTime, date);
//
//    }

    public void start(View v) {
        countUp.start();
    }

    public void stop(View v) {
        countUp.stop();

    }

    public void reset(View v) {
        countUp.reset();
    }

    public void record(View v) {
        Intent intent = new Intent(this, RecordListActivity.class);
        startActivity(intent);
    }





}