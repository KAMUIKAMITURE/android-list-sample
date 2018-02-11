package com.kamiture.kamui.stopwatch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    // test

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

                editor.putLong("CountLong",elapsedTime);
                editor.apply();
            }
        });
        countUp.setOnTickListener(new OnTickListener() {
            @Override
            public void onTick(long elapsedTime) {
                // hh: Hour in sm/pm (1-12)
                // HH: Hour in day (0-23)
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss SSS");
                formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
                String timeString = formatter.format(elapsedTime);
                text.setText(timeString);
                Log.d("event", "経過時間: " + timeString);
            }
        });
        text = findViewById(R.id.time);
    }
    public void start (View v){
        countUp.start();
    }
    public void stop (View v){
        countUp.stop();
    }
    public void reset(View v){
        countUp.reset();
    }
    public void record(View v){
        Intent intent = new Intent(this,RecordListActivity.class);
        startActivity(intent);
    }

}