package com.its.samuele.constatazioneamichevole.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.its.samuele.constatazioneamichevole.R;


/**
 * Created by Utente on 30/09/2016.
 */

public class BottoniFragment extends Fragment {
    Button mDataB,mLuogoB,mDanniB,mFeritiB,mTestimoniB,mContraenteB;
    FloatingActionButton okButton;
    FragmentManager vManager;


    public interface IBottono{
        public void onButtonPress(Fragment f);
    }
    IBottono listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vView = inflater.inflate(R.layout.nuova_constatazione, container, false);
        mDataB = (Button) vView.findViewById(R.id.dataButton);
        mLuogoB = (Button) vView.findViewById(R.id.luogoButton);
        mDanniB = (Button) vView.findViewById(R.id.danniButton);
        mFeritiB = (Button) vView.findViewById(R.id.feritiButton);
        mTestimoniB = (Button) vView.findViewById(R.id.testimoniButton);
        mContraenteB = (Button) vView.findViewById(R.id.contraenteButton);
        okButton = (FloatingActionButton) vView.findViewById(R.id.fab);


        mDataB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mLuogoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LuogoFragment vLuogo = new LuogoFragment();
                vManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = vManager.beginTransaction();
                fragmentTransaction.replace(R.id.container,vLuogo, "Luogo");
                fragmentTransaction.commit();

            }
        });
        mDanniB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DanniFragment vDanni = new DanniFragment();
                vManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = vManager.beginTransaction();
                fragmentTransaction.replace(R.id.container,vDanni, "Danni");
                fragmentTransaction.commit();
            }
        });
        mFeritiB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FeritiFragment vFeriti = new FeritiFragment();
                vManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = vManager.beginTransaction();
                fragmentTransaction.replace(R.id.container,vFeriti, "Feriti");
                fragmentTransaction.commit();

            }
        });
        mTestimoniB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestimoniFragment vTestimoni = new TestimoniFragment();
                vManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = vManager.beginTransaction();
                fragmentTransaction.replace(R.id.container,vTestimoni, "Testimoni");
                fragmentTransaction.commit();

            }

        });
        mContraenteB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return vView;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof IBottono){
            listener = (IBottono) activity;
        }else{
            listener = null;
        }
    }
}
