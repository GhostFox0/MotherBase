package inc.sam.cipherlogin;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Samuele.Pretto on 16/12/2016.
 */

public class RegisterFragment extends Fragment {

    EditText edtEmail, edtPass, edtName, edtSurname, edtAddress;
    Button btnReg;
    TextView txtRegResult;

    public interface IRegisterFragment{
        public void pushRegFragReg(Utente u);
    }

    IRegisterFragment listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_register,container,false);

        edtEmail = (EditText) rootView.findViewById(R.id.editTextRegFragEmail);

        edtPass = (EditText) rootView.findViewById(R.id.editTextRegFragPass);

        edtName = (EditText) rootView.findViewById(R.id.editTextRegFragName);

        edtSurname = (EditText) rootView.findViewById(R.id.editTextRegFragSurn);

        edtAddress = (EditText) rootView.findViewById(R.id.editTextRegFragAddress);

        btnReg = (Button) rootView.findViewById(R.id.buttonRegFragReg);

        txtRegResult = (TextView) rootView.findViewById(R.id.textViewRegFragText);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.pushRegFragReg(new Utente(edtEmail.getText().toString(),edtPass.getText().toString(),edtName.getText().toString(),edtSurname.getText().toString(),edtAddress.getText().toString()));
            }
        });


        return rootView;
    }

    public void setRegText(String s){
        txtRegResult.setText(s);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof IRegisterFragment){
            listener = (IRegisterFragment) activity;
        }
        else {
            listener = null;
        }
    }
}
