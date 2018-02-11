package com.kamiture.kamui.stopwatch;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
        // Item の情報を一つにまとめる.
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        Record item = getItem(position);
//        viewHolder.timeTextView.setText(String.valueOf(item.elapsedTime));
        viewHolder.timeTextView.setText(Utils.formatLongTime(item.elapsedTime));
        viewHolder.dateTextView.setText(String.valueOf(item.date));
        return convertView;
    }

    public RecordAdapter(@NonNull Context context, int resource, @NonNull List<Record> objects) {
        super(context, resource, objects);
        packageManager = context.getPackageManager();
        layoutInflater = LayoutInflater.from(context);

    }

    class ViewHolder {
        TextView timeTextView;
        TextView dateTextView;

        public ViewHolder(View v) {
            timeTextView =  v.findViewById(R.id.item_time);
            dateTextView =  v.findViewById(R.id.item_date);
        }
    }
}
