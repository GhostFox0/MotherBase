package com.its.samuele.constatazioneamichevole.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.its.samuele.constatazioneamichevole.R;
import com.its.samuele.constatazioneamichevole.utility.Testimone;

/**
 * Created by Utente on 27/09/2016.
 */

public class TestimoniFragment extends Fragment {

    EditText edtCognome, edtNome,edtIndirizzo,edtTelefono,edtLocazione;

    Button button;

    public interface ITestimoni{
        public void changeTestimoni(Testimone testimone);
    }

    ITestimoni listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.testimoni_fragment,container,false);
        edtCognome = (EditText) view.findViewById(R.id.edtCognomeTestimone);
        edtNome = (EditText) view.findViewById(R.id.edtNomeTestimone);
        edtIndirizzo = (EditText) view.findViewById(R.id.edtIndirizzoTestimone);
        edtTelefono = (EditText) view.findViewById(R.id.edtTelefonoTestimone);
        edtLocazione = (EditText) view.findViewById(R.id.edtLocazioneTestimone);
        button = (Button) view.findViewById(R.id.btnModTest);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Testimone testimone = new Testimone();
                if (edtNome.length()>0 && edtCognome.length()>0&&edtLocazione.length()>0&&edtTelefono.length()>0&&edtIndirizzo.length()>0){
                    testimone.setCognome(edtCognome.getText().toString());
                    testimone.setIndirizzo(edtIndirizzo.getText().toString());
                    testimone.setNome(edtNome.getText().toString());
                    testimone.setTelefono(edtTelefono.getText().toString());
                    testimone.setLocazione(edtLocazione.getText().toString());
                    listener.changeTestimoni(testimone);
                }
                else {
                    listener.changeTestimoni(testimone);
                }

            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ITestimoni){
            listener = (ITestimoni) activity;
        }
        else {
            listener = null;
        }
    }
}
