package com.kamiture.kamui.stopwatch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.kamiture.kamui.stopwatch.Utils.loadList;
import static com.kamiture.kamui.stopwatch.Utils.saveList;

public class RecordListActivity extends AppCompatActivity {

    private SharedPreferences data;
    ListView listView;
    ArrayAdapter adapter;

    ArrayList<Record> recordList;

    Record record;
    Calendar days = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listView = findViewById(R.id.listview);

        loadList(this);
        // ListView に表示する (Adapter に渡す) List をつくる.

        //recordList = new ArrayList<>();

        Record record = loadAndParseRecord();
        loadList(this).add(record);
        //recordList.add(record);

        // Adapter に渡す, recordList があればいい.
        // recordList には, これまで保存した全てのデータが入っているはず.
        // (Context, 表示に使う layout ファイル, 表示させる List型 (もしくは配列))
        adapter = new RecordAdapter(this, android.R.layout.simple_list_item_1, loadList(this));
        listView.setAdapter(adapter);

        Log.d("MainActivity", listView.toString());
    }

    private Record loadAndParseRecord() {
        // 保存してある long 型, Date 型 のデータをよみだしてくる TOFIX
        data = getSharedPreferences("CountResult", MODE_PRIVATE);
        long elapsedTime = data.getLong("CountLong", 0);

        Calendar rightNow = Calendar.getInstance();
        Date date = rightNow.getTime();

        return new Record(elapsedTime, date);
        // これは, Record 型で保存しておいた方がわかりやすくない？

    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        saveList(this, loadList(this));
    }
}


