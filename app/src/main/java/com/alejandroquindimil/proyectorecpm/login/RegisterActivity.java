package com.alejandroquindimil.proyectorecpm.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.alejandroquindimil.proyectorecpm.Controller.AuthController;
import com.alejandroquindimil.proyectorecpm.Controller.DbController;
import com.alejandroquindimil.proyectorecpm.MainActivity;
import com.alejandroquindimil.proyectorecpm.R;
import com.alejandroquindimil.proyectorecpm.listeners.DbListener;
import com.alejandroquindimil.proyectorecpm.modelos.UserProfile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.firestore.SetOptions;

import java.util.Map;


public class RegisterActivity extends AppCompatActivity {

    private EditText userName, emailTextView, paswordTextView, repeatpassextView;
    private Button BtnRegister,BtnLogin;
    private ProgressBar progressbar;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private Activity act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        mAuth= FirebaseAuth.getInstance();

        userName=findViewById(R.id.act_register_editTest_user);
        emailTextView=findViewById(R.id.act_register_editTextEmail);
        paswordTextView= findViewById(R.id.act_register_editTextPassword);
        repeatpassextView= findViewById(R.id.act_register_editTextrepeatPass);

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
    public void irAMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void registerNewUser() {
        progressbar.setVisibility(View.VISIBLE);

        String user,email, password, confirm;
        user= userName.getText().toString();
        email = emailTextView.getText().toString();
        password = paswordTextView.getText().toString();
        confirm=repeatpassextView.getText().toString();
        progressbar = findViewById(R.id.progressbar);


        if (TextUtils.isEmpty(user)){
            String errorMsg = getString(R.string.error_user_empty);
            userName.setError(errorMsg);
            return;
        }

        if (TextUtils.isEmpty(email)){
            String errorMsg = getString(R.string.error_email_empty);
            emailTextView.setError(errorMsg);
            return;
        }
        if (TextUtils.isEmpty(password)){
            String errorMsg = getString(R.string.error_pass_empty);
            paswordTextView.setError(errorMsg);
            return;
        }

        if (!password.equals(confirm)){
            String errorMsg = getString(R.string.error_confirm_not_equals);
            repeatpassextView.setError(errorMsg);
            return;
        }



        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {

                           // progressbar.setVisibility(View.GONE);
                            currentUser = mAuth.getCurrentUser();
                            String disp = currentUser.getDisplayName();
                            String email = currentUser.getEmail();
                            FirebaseUserMetadata meta = currentUser.getMetadata();
                            String phone = currentUser.getPhoneNumber();
                            String uid = currentUser.getUid();
                            Log.d("USER", currentUser.getUid());
                            irAMain();


                        }else {
                            Exception ex = task.getException();
                            ex.printStackTrace();
                            String errorMsg= getString(R.string.error_singin);
                            userName.setError(errorMsg);
                        }


                    }
                });
        }



    }
