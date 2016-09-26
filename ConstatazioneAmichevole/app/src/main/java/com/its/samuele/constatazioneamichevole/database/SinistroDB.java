package com.its.samuele.constatazioneamichevole.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Utente on 23/09/2016.
 */

public class SinistroDB extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "registrosinistri.db";
    private static final int VERSION = 1;

    public SinistroDB(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SinistroTableHelper.CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
