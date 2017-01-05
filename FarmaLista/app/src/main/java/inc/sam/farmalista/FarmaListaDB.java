package inc.sam.farmalista;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sam on 03/01/17.
 */

//Creation of db for FarmaLista
public class FarmaListaDB extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "farmalista.db";
    public static final int VERSION = 1;

    public FarmaListaDB(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creation of Users Table
        db.execSQL(UserTableHelper.CREATE);
        db.execSQL(MedicineTableHelper.CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
