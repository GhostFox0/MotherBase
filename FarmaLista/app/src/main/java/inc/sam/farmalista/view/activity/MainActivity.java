package inc.sam.farmalista.view.activity;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import inc.sam.farmalista.FarmaListaDB;
import inc.sam.farmalista.R;
import inc.sam.farmalista.controller.UserRegController;
import inc.sam.farmalista.view.fragment.AddMedicineFragment;
import inc.sam.farmalista.view.fragment.ListMedicineFragment;
import inc.sam.farmalista.view.fragment.LoginFragment;
import inc.sam.farmalista.view.fragment.RegistrationFragment;

public class MainActivity extends AppCompatActivity implements LoginFragment.ILoginFragment, ListMedicineFragment.IListMedicineFragment{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (savedInstanceState==null){
            if (!preferences.getBoolean("initTime",false)){
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("initTime",true);
                getFragmentManager().beginTransaction().add(R.id.container,new LoginFragment()).commit();
                editor.commit();
            }
        }
        else{
            getFragmentManager().beginTransaction().replace(R.id.container,new ListMedicineFragment()).commit();
        }

    }

    @Override
    public void onLinkPress() {
        getFragmentManager().beginTransaction().replace(R.id.container,new RegistrationFragment()).commit();
    }

    @Override
    public void onFabPress() {
        getFragmentManager().beginTransaction().replace(R.id.container,new AddMedicineFragment()).commit();
    }
}
