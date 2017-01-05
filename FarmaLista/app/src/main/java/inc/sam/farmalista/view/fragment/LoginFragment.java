package inc.sam.farmalista.view.fragment;

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

import inc.sam.farmalista.R;

/**
 * Created by sam on 02/01/17.
 */

public class LoginFragment extends Fragment {

    private EditText edtLogEmail, edtLogPass;
    private TextView txtLogRegLink;
    private Button btnLogLogin;


    public interface ILoginFragment{
        void onLinkPress();
    }

    public interface ILoginFragmentController{
        void onLoginPress();
    }

    ILoginFragment listener;
    ILoginFragmentController listenerController;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Definition of the Layout
        View rootView = inflater.inflate(R.layout.fragment_login,container,false);
        edtLogEmail = (EditText) rootView.findViewById(R.id.editTextLogEmail);
        edtLogPass = (EditText) rootView.findViewById(R.id.editTextLogPass);
        txtLogRegLink = (TextView) rootView.findViewById(R.id.textViewLogRegLink);
        btnLogLogin = (Button) rootView.findViewById(R.id.buttonLogin);

        txtLogRegLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onLinkPress();
            }
        });

        btnLogLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerController.onLoginPress();
            }
        });

        return rootView;
    }


    public String getLogEmail(){
        return edtLogEmail.getText().toString();
    }

    public String getLogPass(){
        return edtLogPass.getText().toString();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILoginFragment){
            listener = (ILoginFragment) activity;
        }
        else{
            listener = null;
        }
    }
}
