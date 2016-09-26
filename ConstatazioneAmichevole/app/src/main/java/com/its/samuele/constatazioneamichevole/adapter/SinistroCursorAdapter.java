package com.its.samuele.constatazioneamichevole.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.its.samuele.constatazioneamichevole.R;
import com.its.samuele.constatazioneamichevole.database.SinistroTableHelper;

/**
 * Created by Utente on 23/09/2016.
 */

public class SinistroCursorAdapter extends CursorAdapter {

    TextView outputDate, outputTime;

    public SinistroCursorAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.sinistri_layout, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        outputDate = (TextView) view.findViewById(R.id.outputData);
        outputTime = (TextView) view.findViewById(R.id.outputTime);

        outputDate.setText(cursor.getString(cursor.getColumnIndex(SinistroTableHelper.DATA_INSERT)));;
        outputTime.setText(cursor.getString(cursor.getColumnIndex(SinistroTableHelper.TIME)));
    }
}
