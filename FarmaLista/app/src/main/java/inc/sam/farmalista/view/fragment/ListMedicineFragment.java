package inc.sam.farmalista.view.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import inc.sam.farmalista.FarmaListaDB;
import inc.sam.farmalista.MedicineAdapter;
import inc.sam.farmalista.R;
import inc.sam.farmalista.controller.MedicineListController;
import inc.sam.farmalista.model.Medicine;

/**
 * Created by sam on 05/01/17.
 */

public class ListMedicineFragment extends Fragment {

    ListView listMedicine;
    FloatingActionButton floatingActionButtonPlus;
    MedicineListController medicineListController;


    public interface IListMedicineFragment{
        public void onFabPress();
    }

    IListMedicineFragment listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_listmedicine,container,false);
        SQLiteDatabase vFarmaListaDB = new FarmaListaDB(getActivity()).getReadableDatabase();
        medicineListController = new MedicineListController(this);

        listMedicine = (ListView) rootView.findViewById(R.id.listViewMedicine);

        MedicineAdapter medicineAdapter = new MedicineAdapter(getActivity(),medicineListController.getMedicineListDB(vFarmaListaDB));
        listMedicine.setAdapter(medicineAdapter);
        floatingActionButtonPlus = (FloatingActionButton) rootView.findViewById(R.id.fabPlus);

        floatingActionButtonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onFabPress();
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
            if (activity instanceof IListMedicineFragment){
                listener= (IListMedicineFragment) activity;
            }
        else {
                listener=null;
            }
    }
}
