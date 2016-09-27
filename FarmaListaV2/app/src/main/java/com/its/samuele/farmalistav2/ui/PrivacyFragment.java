package com.its.samuele.farmalistav2.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.its.samuele.farmalistav2.R;

import java.util.zip.Inflater;

/**
 * Created by Utente on 07/09/2016.
 */
public class PrivacyFragment extends Fragment {

    Toolbar toolbar;
    SignupFragment signupFragment;
    FragmentManager fragmentManager;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            signupFragment = new SignupFragment();
            fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().addToBackStack(null);
            fragmentTransaction.replace(R.id.container, signupFragment, "tag");
            fragmentTransaction.commit();
        }
        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_privacy, container, false);
        toolbar = (Toolbar) view.findViewById(R.id.toolbarPresentation);
        return view;
    }
}
