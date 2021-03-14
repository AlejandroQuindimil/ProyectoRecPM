package com.alejandroquindimil.proyectorecpm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.Task;

public class RegisterActivity extends AppCompatActivity {

    private EditText emailTextView, paswordTextView;
    private Button Btn;
    private ProgressBar progressbar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        mAuth= FirebaseAuth.getInstance();

        emailTextView=findViewById(R.id.act_register_editTextEmail);
        paswordTextView= findViewById(R.id.act_register_editTextPassword);
        Btn= findViewById(R.id.btn_register_act_register);
        progressbar = findViewById(R.id.progressbar);

        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNewUser();
            }
        });
    }

    private void registerNewUser() {
        progressbar.setVisibility(View.VISIBLE);

        String email, password;
        email = emailTextView.getText().toString();
        password = paswordTextView.getText().toString();
        progressbar = findViewById(R.id.progressbar);
        if (TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(), "Introduce Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(), "Introduce Contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),
                                    "Registro con exito", Toast.LENGTH_LONG).show();

                            progressbar.setVisibility(View.GONE);

                            Intent intent= new Intent(RegisterActivity.this,MainActivity.class);
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
