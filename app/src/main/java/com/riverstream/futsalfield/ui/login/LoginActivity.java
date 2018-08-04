package com.riverstream.futsalfield.ui.login;

import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> implements LoginNavigator {

    @Inject
    LoginViewModel mLoginViewModel;
    private ActivityLoginBinding mActivityLoginBinding;

    public static Intent newIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }
    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void login() {

    }

    @Override
    public void openMainActivity() {

    }
}
