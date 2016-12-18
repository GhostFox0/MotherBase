package com.its.pretto.samuele.marketplace.fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.its.pretto.samuele.marketplace.R;

/**
 * Created by Samuele.Pretto on 13/12/2016.
 */

public class FragmentDialogSocial extends DialogFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dialog,container,false);
        getDialog().setTitle("Social");
        return rootView;
    }
}
