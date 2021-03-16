package com.alejandroquindimil.proyectorecpm.modelos;

import java.util.HashMap;
import java.util.Map;

public class UserProfile {
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
}
