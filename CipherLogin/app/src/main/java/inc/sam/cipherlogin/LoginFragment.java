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

public class LoginFragment extends Fragment {

    EditText edtEmail, edtPassword;
    Button btnLogin;
    TextView logResult;

    public interface ILoginFragment{
        public void pushLogFragLog(Utente u);
    }
    ILoginFragment listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login,container,false);

        edtEmail = (EditText) rootView.findViewById(R.id.editTextLogFragEmail);

        edtPassword = (EditText) rootView.findViewById(R.id.editTextLogFragPass);

        btnLogin = (Button) rootView.findViewById(R.id.buttonLogFragLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.pushLogFragLog(new Utente(edtEmail.getText().toString(),edtPassword.getText().toString(),null,null,null));
            }
        });

        logResult = (TextView) rootView.findViewById(R.id.textViewLogFragText);


        return rootView;
    }

    public void setLogText(String s){
        logResult.setText(s);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILoginFragment){
            listener = (ILoginFragment) activity;
        }
        else {
            listener = null;
        }
    }
}
