package com.its.samuele.farmalistav2.presenter.impl;

import com.google.inject.Singleton;
import com.its.samuele.farmalistav2.presenter.LoginPresenter;
import com.its.samuele.farmalistav2.view.LoginView;

/**
 * Created by Utente on 02/09/2016.
 */
@Singleton
public class LoginPresenterImpl implements LoginPresenter {

    private LoginView view;

    @Override
    public void setView(LoginView view) {
        this.view = view;
    }
}
