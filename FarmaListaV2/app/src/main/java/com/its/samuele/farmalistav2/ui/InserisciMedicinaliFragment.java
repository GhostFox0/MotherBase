package com.its.samuele.farmalistav2.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.its.samuele.farmalistav2.R;

/**
 * Created by Utente on 15/09/2016.
 */
public class InserisciMedicinaliFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inseriscimedicinali,container,false);
        return view;
    }
}
