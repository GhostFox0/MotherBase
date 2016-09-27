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

public class DanniFragment extends Fragment {

    RadioButton altriVeicoli, altriOggetti;
    Button button;
    boolean checkOggetti, checkVeicoli;

    public interface IDanni{
        public boolean changeDanni(boolean a, boolean b);
    };

    IDanni listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.danni_material_fragment,container,false);

        altriVeicoli = (RadioButton) view.findViewById(R.id.radioVeicoli);
        altriOggetti = (RadioButton) view.findViewById(R.id.radioOggetti);
        button = (Button) view.findViewById(R.id.btnModDanni);


        altriVeicoli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkVeicoli = altriVeicoli.isChecked();
            }
        });

        altriOggetti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOggetti = altriOggetti.isChecked();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.changeDanni(checkVeicoli,checkOggetti);
            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof IDanni){
            listener = (IDanni) activity;
        }
        else {
            listener = null;
        }
    }
}
