package inc.sam.farmalista.controller;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import inc.sam.farmalista.MedicineTableHelper;
import inc.sam.farmalista.model.Medicine;
import inc.sam.farmalista.model.User;
import inc.sam.farmalista.view.fragment.AddMedicineFragment;
import inc.sam.farmalista.view.fragment.ListMedicineFragment;

/**
 * Created by sam on 05/01/17.
 */

public class MedicineNewController {

    private AddMedicineFragment addMedicineFragment;

    private Medicine medicine;

    public MedicineNewController(AddMedicineFragment addMedicineFragment) {
        this.addMedicineFragment = addMedicineFragment;
    }

    public void addMedicine(SQLiteDatabase database){
        String nomeMed;
        String quantitaMed;
        String dosaggio;
        String data_inizio;
        String data_scadenza;
        String giorni_preavviso;
        String numero_confezioni;

        try{
            nomeMed = addMedicineFragment.getEdtNameMedicine().toString();
            quantitaMed = addMedicineFragment.getEdtQuantitaMedicine().toString();
            dosaggio = addMedicineFragment.getEdtQuantitaMedicine().toString();
            //TODO In futuro mettere le date giuste con DatePicker
            data_inizio = "05/01/2017";
            data_scadenza = "01/01/01";
            giorni_preavviso = addMedicineFragment.getEdtGiorniPreavviso().toString();
            numero_confezioni = addMedicineFragment.getEdtNumeroConfezioni().toString();
            medicine = new Medicine(nomeMed,quantitaMed,dosaggio,data_inizio,data_scadenza,giorni_preavviso,numero_confezioni);
            ContentValues contentValues = new ContentValues();
            contentValues.put(MedicineTableHelper.NAME,medicine.getName());
            contentValues.put(MedicineTableHelper.QUANTITA,medicine.getQuantita());
            contentValues.put(MedicineTableHelper.DATA_INIZIO,medicine.getData_inizio());
            contentValues.put(MedicineTableHelper.DATA_SCADENZA,medicine.getData_scadenza());
            contentValues.put(MedicineTableHelper.DOSAGGIO,medicine.getDosaggio());
            contentValues.put(MedicineTableHelper.GIORNI_PREAVVISO,medicine.getGiorni_preavviso());
            contentValues.put(MedicineTableHelper.NUMERO_CONFEZIONI,medicine.getNumero_confezioni());
            database.insert(MedicineTableHelper.TABLE_NAME,null,contentValues);
            addMedicineFragment.finishInsert();

        }
        catch (Exception e){

        }
    }



}
