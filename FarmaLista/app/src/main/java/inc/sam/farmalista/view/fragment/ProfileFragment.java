
package inc.sam.farmalista.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import inc.sam.farmalista.R;

/**
 * Created by sam on 03/01/17.
 */

public class ProfileFragment extends Fragment {

    private EditText edtProfName,edtProfSurname,edtProfComune, edtProfAddres;
    private TextView txtProfNameSurn, txtProfGender,txtProfBirthDate,txtProfFiscalCode;
    private Button btnProfUpdate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile,container,false);

        edtProfName = (EditText) rootView.findViewById(R.id.editextProfName);
        edtProfSurname = (EditText) rootView.findViewById(R.id.editextProfSurname);
        edtProfComune = (EditText) rootView.findViewById(R.id.editextProfComune);
        edtProfAddres = (EditText) rootView.findViewById(R.id.editextProfAddress);

        txtProfNameSurn = (TextView) rootView.findViewById(R.id.textViewProfNameSurn);
        txtProfGender = (TextView) rootView.findViewById(R.id.textViewProfGender);
        txtProfBirthDate = (TextView) rootView.findViewById(R.id.textViewProfBirthDate);
        txtProfFiscalCode = (TextView) rootView.findViewById(R.id.textViewProfFiscalCode);

        btnProfUpdate = (Button) rootView.findViewById(R.id.buttonProfUpdate);

        return rootView;
    }


    public String getProfName(){
        return edtProfName.getText().toString();
    }

    public String getProfSurname(){
        return edtProfSurname.getText().toString();
    }

    public String getProfComune(){
        return edtProfComune.getText().toString();
    }

    public String getProfAddress(){
        return edtProfAddres.getText().toString();
    }

    public void displayErrorMessage(String errorMessage){
        Toast.makeText(getActivity(), "Errore "+errorMessage, Toast.LENGTH_SHORT).show();
    }
}
