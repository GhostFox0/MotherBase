package inc.sam.farmalista.view.fragment;

import android.app.Fragment;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import inc.sam.farmalista.FarmaListaDB;
import inc.sam.farmalista.R;
import inc.sam.farmalista.controller.UserRegController;
import inc.sam.farmalista.view.activity.MainActivity;

/**
 * Created by sam on 02/01/17.
 */

public class RegistrationFragment extends Fragment {

    private EditText edtRegEmail,edtRegPass,edtRegConfPass;
    private Button btnRegReg;
    private SQLiteDatabase sqLiteDatabaseFarmaLista;
    private UserRegController userRegController;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Creation of database
        sqLiteDatabaseFarmaLista = new FarmaListaDB(getActivity()).getWritableDatabase();

        //Singleton of the Controller
        userRegController = UserRegController.getInstance(this);

        //Definition of Layout
        View rootView = inflater.inflate(R.layout.fragment_register,container,false);
        edtRegEmail= (EditText) rootView.findViewById(R.id.editTextRegEmail);
        edtRegPass = (EditText) rootView.findViewById(R.id.editTextRegPass);
        edtRegConfPass = (EditText) rootView.findViewById(R.id.editTextRegConfPass);
        btnRegReg = (Button) rootView.findViewById(R.id.buttonRegReg);

        btnRegReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    userRegController.addNewUserListener(sqLiteDatabaseFarmaLista);
            }
        });

        return rootView;
    }

    public void finishRegistration(){
        sqLiteDatabaseFarmaLista.close();
        getFragmentManager().beginTransaction().replace(R.id.container,new ListMedicineFragment()).commit();
    }

    public String getRegEmail(){
        return edtRegEmail.getText().toString();
    }

    public String getRegPass(){
        return edtRegConfPass.getText().toString();
    }

    public String getRegConfPass(){
        return edtRegConfPass.getText().toString();
    }

    public void displayErrorMessage(String errorMessage){
        Toast.makeText(getActivity().getBaseContext(), "Errore "+errorMessage, Toast.LENGTH_SHORT).show();
    }

}
