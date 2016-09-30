package com.its.samuele.constatazioneamichevole.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.its.samuele.constatazioneamichevole.R;

/**
 * Created by Utente on 23/09/2016.
 */
public class Luogo extends Fragment {

    EditText regione, provincia, paese, via;
    Button buttonconferma;

public interface iLuogo {
    public void changeLuogo();
}
    public void onCreate(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View vView =  inflater.inflate(R.layout.fragment_luogo, container, false);
        regione = (EditText) vView.findViewById(R.id.editText);
        provincia = (EditText) vView.findViewById(R.id.editText2);
        paese = (EditText) vView.findViewById(R.id.editText3);
        via = (EditText) vView.findViewById(R.id.editText4);





    }


}
