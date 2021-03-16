package com.alejandroquindimil.proyectorecpm.listeners;

import com.alejandroquindimil.proyectorecpm.modelos.UserProfile;

public interface DbListener {
    void isOk(UserProfile profile);
    void isKo(String error);
}
