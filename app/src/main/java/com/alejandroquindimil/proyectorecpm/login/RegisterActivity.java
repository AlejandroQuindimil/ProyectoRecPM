package com.alejandroquindimil.proyectorecpm.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.alejandroquindimil.proyectorecpm.MainActivity;
import com.alejandroquindimil.proyectorecpm.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;

public class RegisterActivity extends AppCompatActivity {

    private EditText emailTextView, paswordTextView, repeatpaswordTextView;
    private Button BtnRegister,BtnLogin;
    private ProgressBar progressbar;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        mAuth= FirebaseAuth.getInstance();

        emailTextView=findViewById(R.id.act_register_editTextEmail);
        paswordTextView= findViewById(R.id.act_register_editTextPassword);
        repeatpaswordTextView= findViewById(R.id.act_register_editTextrepeatPass);
        BtnRegister= findViewById(R.id.btn_register_act_register);
        BtnLogin= findViewById(R.id.btn_login_act_register);
        progressbar = findViewById(R.id.progressbar);

        BtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNewUser();
            }
        });

        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changetoLogin();
            }
        });
    }

    private void changetoLogin() {
        Intent intent= new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);

    }

    private void registerNewUser() {
        progressbar.setVisibility(View.VISIBLE);

        String email, password, confirm;
        email = emailTextView.getText().toString();
        password = paswordTextView.getText().toString();
        confirm=repeatpaswordTextView.getText().toString();
        progressbar = findViewById(R.id.progressbar);
        if (TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(), "Introduce Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(), "Introduce Contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirm)){
            Toast.makeText(getApplicationContext(), "La contraseña no coincide", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            currentUser= mAuth.getCurrentUser();
                            currentUser = mAuth.getCurrentUser();
                            String disp = currentUser.getDisplayName();
                            String email = currentUser.getEmail();
                            FirebaseUserMetadata meta = currentUser.getMetadata();
                            Uri photo = currentUser.getPhotoUrl();


                            Toast.makeText(getApplicationContext(),
                                    "Registro con exito", Toast.LENGTH_LONG).show();

                            progressbar.setVisibility(View.GONE);

                            Intent intent= new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);

                        }else {
                            Toast.makeText(getApplicationContext(),
                                    "Fallo al registrar", Toast.LENGTH_LONG).show();
                            progressbar.setVisibility(View.GONE);
                        }


                    }
                });

        }
    }
