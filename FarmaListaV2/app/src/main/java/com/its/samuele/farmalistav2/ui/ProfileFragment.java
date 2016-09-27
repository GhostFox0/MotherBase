package com.its.samuele.farmalistav2.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.its.samuele.farmalistav2.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import android.widget.AdapterView.OnItemSelectedListener;

/**
 * Created by Utente on 15/09/2016.
 */
public class ProfileFragment extends Fragment {


    DatePicker datePicker;
    Calendar calendar;
    TextView dateView;
    private int year, month,day;
    Spinner spinner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container,false);

        dateView = (TextView) view.findViewById(R.id.input_data_nascita);

        spinner = (Spinner) view.findViewById(R.id.spinner);

        List<String> sessoScelta = new ArrayList<String>();
        sessoScelta.add("Maschio");
        sessoScelta.add("Femmina");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,sessoScelta);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }
}
