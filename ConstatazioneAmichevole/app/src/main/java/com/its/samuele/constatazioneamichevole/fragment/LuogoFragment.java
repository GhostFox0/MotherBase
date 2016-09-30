package com.its.samuele.constatazioneamichevole.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.its.samuele.constatazioneamichevole.R;

/**
 * Created by Samuele.Pretto on 30/09/2016.
 */

public class LuogoFragment extends Fragment {

    EditText edtPaese, edtVia;

    public interface ILuogo{
        public void changeLuogo();
    }

    ILuogo listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.luogo_fragment, container,false);

        edtPaese = (EditText) view.findViewById(R.id.editTextPaese);
        edtVia = (EditText) view.findViewById(R.id.editTextVia);



        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILuogo){
            listener = (ILuogo) activity;
        }
        else {
            listener = null;
        }
    }
}
