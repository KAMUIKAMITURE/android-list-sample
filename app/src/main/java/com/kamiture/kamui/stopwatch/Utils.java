package com.kamiture.kamui.stopwatch;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by kamui_000 on 2018/02/11.
 */

public class Utils {

    public static final String SAVE_KEY = "saved_list";


    // 使い方
    //
    // 読み込む時
    // 保存されている List のデータを, ArrayList<Record> 型の変数に代入する.
    // ArrayList<Record> list = loadList();
    //
    // 保存する時
    // list の変数を SharedPreference に保存する.
    // saveList(list);
    public static ArrayList<Record> loadList(Context context) {
        ArrayList<Record> arrayList; // 空の ArrayList 型の変数を宣言する.
        // この時点では、 インスタンスは生成していない (new していない).

        SharedPreferences pref = context.getSharedPreferences("pref", MODE_PRIVATE);
        Gson gson = new Gson();
        // 保存したのは String 型なので, String 型でならデータを入手できる.
        // saveList メソッドの時に, targetData として保存したデータのこと.
        String data = pref.getString(SAVE_KEY, "");

        arrayList = gson.fromJson(data, new TypeToken<ArrayList<Record>>(){}.getType());

        if (arrayList == null) arrayList = new ArrayList<>();
        return arrayList;
        // Gson が
        // ArrayList<Record> 型の変数を
        // String 型に変換している.
    }

    public static void saveList(Context context, ArrayList<Record> list) {
        SharedPreferences pref = context.getSharedPreferences("pref", MODE_PRIVATE);
        Gson gson = new Gson();
        // 変数 list のメモリ上のデータを, そのまま String 型として読み換える (変換する).
        String targetData = gson.toJson(list);
        Log.d("RecordActivity", targetData);

        pref.edit().putString(SAVE_KEY, targetData).apply();
    }

}
