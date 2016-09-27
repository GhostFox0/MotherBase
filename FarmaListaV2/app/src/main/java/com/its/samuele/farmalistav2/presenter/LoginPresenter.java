package com.its.samuele.farmalistav2.presenter;

import com.google.inject.ImplementedBy;
import com.its.samuele.farmalistav2.presenter.impl.LoginPresenterImpl;
import com.its.samuele.farmalistav2.view.LoginView;

/**
 * Created by Utente on 02/09/2016.
 */
@ImplementedBy(LoginPresenterImpl.class)
public interface LoginPresenter {

    void setView(LoginView view);
}
