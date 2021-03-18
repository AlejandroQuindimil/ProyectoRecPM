package com.alejandroquindimil.proyectorecpm.Controller;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alejandroquindimil.proyectorecpm.R;
import com.alejandroquindimil.proyectorecpm.listeners.DbListener;
import com.alejandroquindimil.proyectorecpm.modelos.UserProfile;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.SetOptions;

import java.util.Map;

public class DbController {
    private static final String USERS_COLLECTION = "Users";

    private FirebaseFirestore db;
    private Activity act;
    private FirebaseAuth mAuth;

    private DbController(){
        db = FirebaseFirestore.getInstance();
    }
    private static DbController instance;

    public static DbController init(Activity act){
        if (instance == null){
            instance = new DbController();
        }
        instance.act = act;
        instance.getAuth();
        return instance;
    }

    private void getAuth() {
        mAuth = AuthController.init(act).getAuth();
    }

    public FirebaseUser getUser(){

        return mAuth.getCurrentUser();
    }

    public void saveProfile(UserProfile profile, DbListener listener){
        FirebaseUser user = getUser();
        String uid = user.getUid();
        Map map = profile.toMap();
        map.put("uid", uid);
        db.collection(USERS_COLLECTION)
                .document(uid)
                .set(map, SetOptions.merge())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        listener.isOk(profile);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception ex) {
                        ex.printStackTrace();
                        String error = ex.getLocalizedMessage();
                        if (listener != null) {
                            listener.isKo(error);
                        }
                    }
                });
    }

    public void getUserProfile(DbListener listener){
        FirebaseUser user = getUser();
        String uid = user.getUid();

        DocumentReference docRef = db.collection(USERS_COLLECTION).document(uid);
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {

            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null && value.exists()) {
                    UserProfile obj = value.toObject(UserProfile.class);
                    saveOnShared(obj);
                    if (listener != null){
                        listener.isOk(obj);
                    }
                }else{
                    String err = act.getString(R.string.error_profile_not_exist);
                    if (listener != null){
                        listener.isKo(err);
                    }
                }
            }
        });
    }

    private void saveOnShared(UserProfile user) {
        if (user == null){
            return;
        }

        String fileName = act.getString(R.string.sharedpreferences_file);
        SharedPreferences sharedPref = act.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putStringSet("profile", user.toMap().entrySet() );

        editor.commit();

    }


}
