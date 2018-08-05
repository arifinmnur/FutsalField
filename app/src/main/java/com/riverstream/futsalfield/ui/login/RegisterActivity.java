package com.riverstream.futsalfield.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.riverstream.futsalfield.MainActivity;
import com.riverstream.futsalfield.R;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";
    FirebaseDatabase database;
    DatabaseReference ref;
    private FirebaseAuth mAuth;

    private String name,city,email,password;
    private TextInputEditText nameTV,cityTV,emailTV,passwordTV;
    private Button buttonRegister;
    private TextView linkLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nameTV = (TextInputEditText) findViewById(R.id.name);
        cityTV = (TextInputEditText) findViewById(R.id.city);
        emailTV = (TextInputEditText) findViewById(R.id.etEmail);
        passwordTV = (TextInputEditText) findViewById(R.id.etPassword);
        linkLogin = (TextView) findViewById(R.id.linkLogin);
        buttonRegister = (Button) findViewById(R.id.btnServerRegister);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference().child("Users");

        //DatabaseReference usersRef = ref.child("user");
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        
        buttonRegister.setOnClickListener((View v) -> {
            name = nameTV.getText().toString();
            city = cityTV.getText().toString();
            email = emailTV.getText().toString();
            password = passwordTV.getText().toString();

            mAuth.createUserWithEmailAndPassword(email, password)
              .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {
                      if (task.isSuccessful()){
                          Log.d(TAG, "createUserWithEmail:success");
                          FirebaseUser user = mAuth.getCurrentUser();
                          startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                          finish();
                      } else{
                          Log.w(TAG, "createUserWithEmail:failure", task.getException());
                          Toast.makeText(RegisterActivity.this,"Authentication failed.",Toast.LENGTH_SHORT).show();
                      }

                  }
              });
        });
        linkLogin.setOnClickListener((View v)-> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        });
    }
}