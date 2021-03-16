package com.alejandroquindimil.proyectorecpm.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.alejandroquindimil.proyectorecpm.R;
import com.alejandroquindimil.proyectorecpm.login.RegisterActivity;
import com.google.firebase.auth.FirebaseAuth;

public class UserFragment extends Fragment {
    
    private Button btnLogout,btnEditar;
    private EditText Usuario, Username, Email;

    private FirebaseAuth mAuth;

    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mAuth= FirebaseAuth.getInstance();


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

    }

    private void doLogout() {
        
        Intent intent = new Intent(getActivity(), RegisterActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    private void initViews(View v) {
        btnLogout= v.findViewById(R.id.frg_userbtn_logOut);
        btnEditar =v.findViewById(R.id.frg_user_btn_edit);

        Usuario= v.findViewById(R.id.frg_user_tView_userName);
        Username = v.findViewById(R.id.frg_user_name_tviem);
        Email = v.findViewById(R.id.frg_user_email_tView);



    }
}
