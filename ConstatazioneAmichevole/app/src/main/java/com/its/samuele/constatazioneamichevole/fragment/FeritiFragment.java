package com.its.samuele.constatazioneamichevole.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import com.its.samuele.constatazioneamichevole.R;

/**
 * Created by Utente on 27/09/2016.
 */

public class FeritiFragment extends Fragment {


    RadioButton radioButton;
    Button button;
    boolean check;

    public interface IFeriti{
        public boolean changeFeriti(boolean b);
    }

    IFeriti listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.feriti_fragment,container, false);
        radioButton = (RadioButton) view.findViewById(R.id.radioFeriti);
        button = (Button) view.findViewById(R.id.feritiModifica);

        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = radioButton.isChecked();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.changeFeriti(check);
            }
        });
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof IFeriti){
            listener = (IFeriti) activity;
        }
        else {
            listener = null;
        }
    }
}


