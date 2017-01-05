package inc.sam.farmalista.view.fragment;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import inc.sam.farmalista.FarmaListaDB;
import inc.sam.farmalista.R;
import inc.sam.farmalista.controller.MedicineNewController;

/**
 * Created by sam on 05/01/17.
 */

public class AddMedicineFragment extends Fragment {

    private Button btnInsertMedicine;
    private TextView txtInitDateMedicine, txtDataScadenzaMedicine;
    private EditText edtNameMedicine, edtQuantitaMedicine, edtDosaggioMedicina,edtGiorniPreavviso,edtNumeroConfezioni;
    private SQLiteDatabase sqLiteDatabase;
    private MedicineNewController medicineNewController;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_addmedicine,container,false);

        sqLiteDatabase = new FarmaListaDB(getActivity()).getWritableDatabase();
        medicineNewController = new MedicineNewController(this);

        txtInitDateMedicine = (TextView) rootView.findViewById(R.id.textViewInitDateMedicine);
        txtDataScadenzaMedicine = (TextView) rootView.findViewById(R.id.textViewDataScadenzaMedicina);
        edtNameMedicine = (EditText) rootView.findViewById(R.id.editTextNameMedicine);
        edtQuantitaMedicine = (EditText) rootView.findViewById(R.id.editTextQuantitaMedicina);
        edtDosaggioMedicina = (EditText) rootView.findViewById(R.id.editTextDosaggioMedicina);
        edtGiorniPreavviso = (EditText) rootView.findViewById(R.id.editTextGiorniPreavviso);
        edtNumeroConfezioni = (EditText) rootView.findViewById(R.id.editTextNumeroConfezioni);
        btnInsertMedicine = (Button) rootView.findViewById(R.id.buttonInserisci);

        btnInsertMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medicineNewController.addMedicine(sqLiteDatabase);
            }
        });

        return rootView;
    }

    public String getEdtNameMedicine() {
        return edtNameMedicine.getText().toString();
    }

    public String getEdtQuantitaMedicine() {
        return edtQuantitaMedicine.getText().toString();
    }

    public String getEdtDosaggioMedicina() {
        return edtDosaggioMedicina.getText().toString();
    }

    public String getEdtGiorniPreavviso() {
        return edtGiorniPreavviso.getText().toString();
    }

    public String getEdtNumeroConfezioni() {
        return edtNumeroConfezioni.getText().toString();
    }

    public String getTxtInitDateMedicine() {
        return txtInitDateMedicine.getText().toString();
    }

    public String getTxtDataScadenzaMedicine() {
        return txtDataScadenzaMedicine.getText().toString();
    }

    public void finishInsert(){
        sqLiteDatabase.close();
        getFragmentManager().beginTransaction().replace(R.id.container,new ListMedicineFragment()).commit();
    }
}
