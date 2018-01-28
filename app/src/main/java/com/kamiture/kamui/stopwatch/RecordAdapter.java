package com.kamiture.kamui.stopwatch;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.icu.text.AlphabeticIndex;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lifeistech on 2018/01/28.
 */

public class RecordAdapter extends ArrayAdapter<Record>{
    PackageManager packageManager;
    LayoutInflater layoutInflater;

    int resId;


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item, parent, false);
        }

        Record item = getItem(position);
        TextView time =  convertView.findViewById(R.id.item_time);
        time.setText(String.valueOf(item.elapsedTime));
        TextView date =  convertView.findViewById(R.id.item_date);
        date.setText(String.valueOf(item.date));
        return convertView;
    }

    public RecordAdapter(@NonNull Context context, int resource, @NonNull List<Record> objects) {
        super(context, resource, objects);
        packageManager = context.getPackageManager();
        layoutInflater = LayoutInflater.from(context);

    }
}
