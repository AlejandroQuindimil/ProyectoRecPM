package com.alejandroquindimil.proyectorecpm.Controller;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.alejandroquindimil.proyectorecpm.listeners.AuthListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthController {
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private Activity act;

    private AuthController() {
    }

    private static AuthController instance;

    public static AuthController init(Activity act) {
        if (instance == null) {
            instance = new AuthController();
        }
        instance.act = act;
        instance.checkUser();
        return instance;
    }

    private void checkUser() {
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
    }

    public FirebaseAuth getAuth() {
        return this.mAuth;
    }

    public boolean isLoged() {
        return (currentUser != null);
    }

    private void checkTask(Task task, AuthListener listener) {
        if (!task.isSuccessful()) {
            currentUser = null;
            // error
            Exception ex = task.getException();
            ex.printStackTrace();
            String error = ex.getLocalizedMessage();
            if (listener != null) {
                listener.isKo(error);
            }
            return;
        }

        FirebaseUser user = mAuth.getCurrentUser();
        currentUser = user;
        if (listener != null) {
            listener.isOk(user);
        }

    }

    private OnCompleteListener<AuthResult> getCompletedTas(AuthListener listener) {

        OnCompleteListener<AuthResult> task = new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                checkTask(task, listener);
            }
        };

        return task;
    }

    public void register(String email, String password, AuthListener listener) {
        OnCompleteListener<AuthResult> task = this.getCompletedTas(listener);
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(act, task);
    }

    public void login(String email, String password, AuthListener listener) {
        OnCompleteListener<AuthResult> task = this.getCompletedTas(listener);


        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(act, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    DbController.init(act).getUserProfile(null);
                    if (listener != null) {
                        listener.isOk(mAuth.getCurrentUser());
                    }
                } else {
                    if (listener != null) {
                        Exception e = task.getException();
                        listener.isKo(e.getLocalizedMessage());
                    }
                }
            }
        });
    }

    public void logOut() {
        mAuth.signOut();
    }

    public void recovery(String email, AuthListener listener) {
        OnCompleteListener<Void> task = new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (!task.isSuccessful()) {
                    Exception ex = task.getException();
                    if (listener != null) {
                        listener.isKo(ex.getLocalizedMessage());
                    }
                    return;
                }
                if (listener != null) {
                    listener.isOk(null);
                }
            }
        };
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(act, task);
    }
}
