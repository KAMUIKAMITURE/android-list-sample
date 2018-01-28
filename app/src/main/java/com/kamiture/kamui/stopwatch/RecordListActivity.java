package com.kamiture.kamui.stopwatch;

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

public class RecordListActivity extends AppCompatActivity {

    public static final String SAVE_KEY = "saved_list";

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
        // ListView に表示する (Adapter に渡す) List をつくる.

        recordList = new ArrayList<>();

        Record record = loadAndParseRecord();
        recordList.add(record);
        recordList.add(record);
        recordList.add(record);
        recordList.add(record);

        // Adapter に渡す, recordList があればいい.
        // recordList には, これまで保存した全てのデータが入っているはず.
        // (Context, 表示に使う layout ファイル, 表示させる List型 (もしくは配列))
        adapter = new RecordAdapter(this, android.R.layout.simple_list_item_1, recordList);
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


    // 使い方
    //
    // 読み込む時
    // 保存されている List のデータを, ArrayList<Record> 型の変数に代入する.
    // ArrayList<Record> list = loadList();
    //
    // 保存する時
    // list の変数を SharedPreference に保存する.
    // saveList(list);
    private ArrayList<Record> loadList() {
        ArrayList<Record> arrayList; // 空の ArrayList 型の変数を宣言する.
        // この時点では、 インスタンスは生成していない (new していない).

        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        Gson gson = new Gson();
        // 保存したのは String 型なので, String 型でならデータを入手できる.
        // saveList メソッドの時に, targetData として保存したデータのこと.
        String data = pref.getString(SAVE_KEY, "");

        arrayList = gson.fromJson(data, new TypeToken<ArrayList<Record>>(){}.getType());
        return arrayList;
        // Gson が
        // ArrayList<Record> 型の変数を
        // String 型に変換している.
    }

    private void saveList(ArrayList<Record> list) {
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        Gson gson = new Gson();
        // 変数 list のメモリ上のデータを, そのまま String 型として読み換える (変換する).
        String targetData = gson.toJson(list);
        Log.d("RecordActivity", targetData);

        pref.edit().putString(SAVE_KEY, targetData).apply();

    }
}


