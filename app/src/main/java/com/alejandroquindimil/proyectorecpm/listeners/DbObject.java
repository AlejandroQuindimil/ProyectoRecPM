package com.alejandroquindimil.proyectorecpm.listeners;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.Map;

public interface DbObject {
    Map toMap();
    Object toObject(DocumentSnapshot doc);
}
