package com.its.samuele.farmalistav2.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.its.samuele.farmalistav2.R;

/**
 * Created by Utente on 12/09/2016.
 */
public class ListaMedicinaliFragment extends Fragment {

    FloatingActionButton fab;
    InserisciMedicinaliFragment insertMedicine;
    FragmentManager manager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_listamedicinali, container, false);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertMedicine = new InserisciMedicinaliFragment();
                manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.container,insertMedicine,"fra");
                transaction.commit();
            }
        });

        return view;
    }
}
