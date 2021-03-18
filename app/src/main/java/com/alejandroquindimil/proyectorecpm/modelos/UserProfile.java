package com.alejandroquindimil.proyectorecpm.modelos;

import com.alejandroquindimil.proyectorecpm.listeners.DbObject;
import com.google.firebase.firestore.DocumentSnapshot;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class UserProfile implements DbObject, Serializable {
    private String name="";
    private String email="";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map toMap() {
        HashMap map= new HashMap();
        map.put("name", this.name);
        map.put("email", this.email);
        return map;
    }

    @Override
    public Object toObject(DocumentSnapshot doc) {
        return null;
    }
}
