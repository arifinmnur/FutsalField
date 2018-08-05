package com.riverstream.futsalfield.ui.login;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.riverstream.futsalfield.R;

public class LoginActivity extends AppCompatActivity {
    public ProgressBar mProgressBar;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    private String email, password;
    private TextInputEditText emailTV, passwordTV;
    public Button buttonLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        emailTV     = (TextInputEditText) findViewById(R.id.etEmail);
        passwordTV  = (TextInputEditText) findViewById(R.id.etPassword);
        buttonLogin = (Button) findViewById(R.id.btnServerLogin);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();

        mUser= mAuth.getCurrentUser();
        if (mUser != null){
            Toast.makeText(this,"Anda Sudah Login",Toast.LENGTH_SHORT);
            Log.v("Hello","Anda Sudah Login email "+ mUser.getEmail()+" password "+mUser.getUid());
        }

        buttonLogin.setOnClickListener((View v)->{
            email       = emailTV.getText().toString();
            password    = passwordTV.getText().toString();
            Log.v("Hello","email "+email+" password "+password);
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(LoginActivity.this, "Hi "+user.getEmail(),
                                Toast.LENGTH_LONG).show();
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }

                }
            });
          });


    }
}
