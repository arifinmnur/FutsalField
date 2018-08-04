package com.riverstream.futsalfield.ui.login;

import android.arch.lifecycle.ViewModel;
import android.text.TextUtils;

import com.riverstream.futsalfield.data.DataManager;
import com.riverstream.futsalfield.ui.Base.BaseViewModel;
import com.riverstream.futsalfield.utils.CommonUtils;
import com.riverstream.futsalfield.utils.rx.SchedulerProvider;

public class LoginViewModel extends BaseViewModel<LoginNavigator>{
    public LoginViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public boolean isEmailAndPasswordValid(String email, String password) {
        // validate email and password
        if (TextUtils.isEmpty(email)) {
            return false;
        }
        if (!CommonUtils.isEmailValid(email)) {
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            return false;
        }
        return true;
    }

    public void login(String email, String password) {
    }

    public void onFbLoginClick() {
    }

    public void onGoogleLoginClick() {
    }

    public void onServerLoginClick() {
    }
}
