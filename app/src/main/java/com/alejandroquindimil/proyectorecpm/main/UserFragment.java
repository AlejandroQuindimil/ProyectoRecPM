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

import com.alejandroquindimil.proyectorecpm.Controller.DbController;
import com.alejandroquindimil.proyectorecpm.R;
import com.alejandroquindimil.proyectorecpm.login.RegisterActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class UserFragment extends Fragment {
    
    private Button btnLogout,btnEditar;
    private EditText Username, Email;

    private FirebaseAuth mAuth;


    FirebaseFirestore db= FirebaseFirestore.getInstance();

    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mAuth= FirebaseAuth.getInstance();

        Map<String, Object> user = new HashMap<>();
        user.put("Prueba", "");
        user.put("Prueba2", "");


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
        initListeners();
        
        return v;
    }

    private void initListeners() {
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLogout();
            }
        });
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editProfile();
            }
        });
    }

    private void editProfile() {
        Map<String, Object> data = new HashMap<>();
        data.put("Name", Username.getText().toString());
        data.put("Email", Email.getText().toString());
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

        Username = v.findViewById(R.id.frg_user_txt_name);
        Email = v.findViewById(R.id.frg_user_txt_correo);


    }


}
