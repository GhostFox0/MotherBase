package inc.sam.farmalista.controller;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Toast;

import inc.sam.farmalista.FarmaListaDB;
import inc.sam.farmalista.UserTableHelper;
import inc.sam.farmalista.model.User;
import inc.sam.farmalista.view.fragment.LoginFragment;
import inc.sam.farmalista.view.fragment.ProfileFragment;
import inc.sam.farmalista.view.fragment.RegistrationFragment;

/**
 * Created by sam on 03/01/17.
 */


//Controller for View RegistrationFragment, ProfileFragment, LoginFragment and Model User
public class UserRegController {
    private RegistrationFragment registrationFragment;
    private static UserRegController instance;
    private User user;

    public  static UserRegController getInstance(RegistrationFragment registration){
        if (instance==null){
            instance = new UserRegController(registration);
        }
        return instance;
    }

    public UserRegController(RegistrationFragment registrationFragment) {
        this.registrationFragment = registrationFragment;
    }

    public void addNewUserListener(SQLiteDatabase sqLiteDatabase) {
        String email,password, confpassword;

        try {
            email =  registrationFragment.getRegEmail();
            password = registrationFragment.getRegPass();
            confpassword = registrationFragment.getRegConfPass();
            if (password.equals(confpassword)){
                user = new User(email,password);
                ContentValues contentValues = new ContentValues();
                contentValues.put(UserTableHelper.EMAIL,user.getName());
                contentValues.put(UserTableHelper.PASSWORD,user.getPassword());
                sqLiteDatabase.insert(UserTableHelper.TABLE_NAME,null,contentValues);
                registrationFragment.finishRegistration();
            }
            else{
                sqLiteDatabase.close();
                registrationFragment.displayErrorMessage("Errore");
            }
        }
        catch (Exception e){
            registrationFragment.displayErrorMessage(e.toString());
        }

    }
}
