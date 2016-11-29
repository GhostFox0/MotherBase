package com.its.pretto.samuele.marketplace.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.its.pretto.samuele.marketplace.R;

/**
 * Created by Samuele.Pretto on 22/11/2016.
 */

public class FragmentAddAds extends Fragment {

    ImageButton imgBtn;
    EditText edtTitle, edtDesc, edtPrice, edtEmail, edtRegione, edtProvincia, edtComune;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_add,container,false);
        imgBtn = (ImageButton) rootView.findViewById(R.id.imageButtonImage);
        edtTitle = (EditText) rootView.findViewById(R.id.editTextTitle);
        edtDesc = (EditText) rootView.findViewById(R.id.editTextDesc);
        edtPrice = (EditText) rootView.findViewById(R.id.editTextPrice);
        edtEmail = (EditText) rootView.findViewById(R.id.editTextEmail);
        edtRegione = (EditText) rootView.findViewById(R.id.editTextRegione);
        edtProvincia = (EditText) rootView.findViewById(R.id.editTextProvincia);
        edtComune = (EditText) rootView.findViewById(R.id.editTextComune);

        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        return rootView;
    }
}
