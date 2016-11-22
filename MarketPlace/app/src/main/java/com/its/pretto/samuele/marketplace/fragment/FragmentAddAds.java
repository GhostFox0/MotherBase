package com.its.pretto.samuele.marketplace.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.its.pretto.samuele.marketplace.R;

/**
 * Created by Samuele.Pretto on 22/11/2016.
 */

public class FragmentAddAds extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_add,container,false);

        return rootView;
    }
}
