package com.alejandroquindimil.proyectorecpm.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.Button;

import com.alejandroquindimil.proyectorecpm.MainActivity;
import com.alejandroquindimil.proyectorecpm.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;

public class LoginActivity extends AppCompatActivity {

    private EditText emailTextView, passwordTextView;
    private Button Btniniciar, Btnregistar;
    private ProgressBar progressbar;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        emailTextView = findViewById(R.id.act_login_editTextEmail);
        passwordTextView = findViewById(R.id.act_login_editTextPassword);
        Btniniciar = findViewById(R.id.btn_login_act_login_);
        Btnregistar = findViewById(R.id.btn_register_act_login);
        progressbar = findViewById(R.id.progressBar);

        Btniniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUserAccount();
            }
        });

        Btnregistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changetoRegister();
            }
        });


    }

    private void changetoRegister() {
        Intent intent= new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);

    }

    public void irAMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void loginUserAccount() {

        progressbar.setVisibility(View.VISIBLE);

        String email, password;
        email = emailTextView.getText().toString();
        password = passwordTextView.getText().toString();

        if (TextUtils.isEmpty(email)) {
            String errorMsg = getString(R.string.error_email_empty);
            emailTextView.setError(errorMsg);
            return;
        }

        if (TextUtils.isEmpty(password)) {
            String errorMsg = getString(R.string.error_pass_empty);
            passwordTextView.setError(errorMsg);
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    currentUser = mAuth.getCurrentUser();
                                    String disp = currentUser.getDisplayName();
                                    String email = currentUser.getEmail();
                                    FirebaseUserMetadata meta = currentUser.getMetadata();

                                    String phone = currentUser.getPhoneNumber();
                                    String uid = currentUser.getUid();
                                    Log.d("USER", currentUser.getUid());
                                    irAMain();


                                } else {
                                    Exception ex = task.getException();
                                    ex.printStackTrace();
                                    String errorMsg= getString(R.string.error_singin);
                                    emailTextView.setError(errorMsg);
                                }
                            }
                        });
    }



}