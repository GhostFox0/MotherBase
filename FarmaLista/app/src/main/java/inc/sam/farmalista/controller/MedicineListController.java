package inc.sam.farmalista.controller;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import inc.sam.farmalista.MedicineTableHelper;
import inc.sam.farmalista.model.Medicine;
import inc.sam.farmalista.model.User;
import inc.sam.farmalista.view.fragment.ListMedicineFragment;

/**
 * Created by sam on 05/01/17.
 */

public class MedicineListController {

    private ListMedicineFragment listMedicineFragment;

    private User user;

    public MedicineListController(ListMedicineFragment listMedicineFragment) {
        this.listMedicineFragment = listMedicineFragment;
    }

    public ArrayList<Medicine> getMedicineListDB(SQLiteDatabase sqLiteDatabase){

        //TODO Inserire selezione where id_utente= id dell'Utente loggato
        Cursor vCursor = sqLiteDatabase.query(MedicineTableHelper.TABLE_NAME,null,null,null,null,null,null);
        int vIndexName = vCursor.getColumnIndex(MedicineTableHelper.NAME);
        int vIndexDataInizio = vCursor.getColumnIndex(MedicineTableHelper.DATA_INIZIO);
        int vIndexDataScadenza = vCursor.getColumnIndex(MedicineTableHelper.DATA_SCADENZA);
        int vIndexDosaggio = vCursor.getColumnIndex(MedicineTableHelper.DOSAGGIO);
        int vIndexGiorniPreavviso = vCursor.getColumnIndex(MedicineTableHelper.GIORNI_PREAVVISO);
        int vIndexNumeroConfezioni = vCursor.getColumnIndex(MedicineTableHelper.NUMERO_CONFEZIONI);
        int vIndexQuantita = vCursor.getColumnIndex(MedicineTableHelper.QUANTITA);

        ArrayList<Medicine> medicines = new ArrayList<>();
        while (vCursor.moveToNext()){
            Medicine medicine = new Medicine(vCursor.getString(vIndexName),vCursor.getString(vIndexQuantita),vCursor.getString(vIndexDosaggio),vCursor.getString(vIndexDataInizio),vCursor.getString(vIndexDataScadenza),vCursor.getString(vIndexGiorniPreavviso),vCursor.getString(vIndexNumeroConfezioni));
            medicines.add(medicine);
        }
        vCursor.close();
        sqLiteDatabase.close();
        return medicines;
    }

}
