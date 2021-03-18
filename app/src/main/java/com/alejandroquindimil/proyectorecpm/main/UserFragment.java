package com.alejandroquindimil.proyectorecpm.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.alejandroquindimil.proyectorecpm.Controller.BaseFrg;
import com.alejandroquindimil.proyectorecpm.Controller.DbController;
import com.alejandroquindimil.proyectorecpm.R;
import com.alejandroquindimil.proyectorecpm.listeners.DbListener;
import com.alejandroquindimil.proyectorecpm.login.RegisterActivity;
import com.alejandroquindimil.proyectorecpm.modelos.UserProfile;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class UserFragment extends BaseFrg {
    
    private Button btnLogout,btnEditar, btnMostar;
    private EditText Username, Email , Phone, Direccion;
    private EditText edtName, edtEmail;

    private FirebaseAuth mAuth;
    private UserProfile currentProfile;
    private DbController dbCtrl;

    FirebaseFirestore db= FirebaseFirestore.getInstance();

    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mAuth= FirebaseAuth.getInstance();

        Map<String, Object> user = new HashMap<>();

        db.collection("users").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });

        View v = inflater.inflate(R.layout.fragment_user, container, false);
        initViews(v);
        dbCtrl = DbController.init(getActivity());
        initListeners();
        getUserProfile();



        return v;
    }

    private void initListeners() {
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { doLogout();
            }
        });
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { editProfile();
            }
        });
        btnMostar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    private void saveProfile() {
        String name= Username.getText().toString().trim();
        String email= Email.getText().toString().trim();

        UserProfile profile = new UserProfile();
        profile.setName(name);
        profile.setEmail(email);


        showLoading(true);
        hideKeyb();
        dbCtrl.saveProfile(profile, new DbListener() {
            @Override
            public void isOk(UserProfile profile) {
                String msg = getString(R.string.request_save_profile_ok);
                showInfo(msg);
                showLoading(false);
            }

            @Override
            public void isKo(String error) {
                showLoading(false);
                showError(error);
            }
        });

    }


    private void getUserProfile() {
        showLoading(true);
        dbCtrl.getUserProfile(new DbListener() {
            @Override
            public void isOk(UserProfile profile) {
                showLoading(false);
                currentProfile = profile;
                updateProfile();
            }

            @Override
            public void isKo(String error) {
                showLoading(false);
                Log.e("Settings", error);
            }
        });
    }

    private void updateProfile() {
        if (currentProfile == null){
            return;
        }
        Username.setText(currentProfile.getName());
        Email.setText(currentProfile.getEmail());

    }

    private void editProfile() {
        Map<String, Object> data = new HashMap<>();
        data.put("Name", Username.getText().toString());
        data.put("Email", Email.getText().toString());
        data.put("Telefono", Phone.getText().toString());
        data.put("Direccion", Direccion.getText().toString());
        
    db.collection("users").document()
            .set(data, SetOptions.merge());

    }

    private void doLogout() {
        
        Intent intent = new Intent(getActivity(), RegisterActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    private void initViews(View v) {
        btnLogout= v.findViewById(R.id.frg_userbtn_logOut);
        btnEditar =v.findViewById(R.id.frg_user_btn_edit);
        btnMostar=v.findViewById(R.id.frg_user_btn_mostrar);
        
        Username = v.findViewById(R.id.frg_user_txt_name);
        Email = v.findViewById(R.id.frg_user_txt_correo);
        Phone = v.findViewById(R.id.frg_user_txt_telefono);
        Direccion = v.findViewById(R.id.frg_user_txt_direcion);
    }


}
