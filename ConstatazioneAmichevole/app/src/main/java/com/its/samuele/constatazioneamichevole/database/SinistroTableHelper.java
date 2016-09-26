package com.its.samuele.constatazioneamichevole.database;

import android.provider.BaseColumns;

/**
 * Created by Utente on 23/09/2016.
 */

public class SinistroTableHelper implements BaseColumns {

    public static final String TABLE_NAME = "sinistri";
    public static final String DATA_INSERT = "date_insert";
    public static final String TIME = "time";

    public static final String CREATE = "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME+" ( "+_ID + "INTEGER PRIMARY KEY, "+DATA_INSERT+" DATE, "+TIME+" TEXT);";

}
