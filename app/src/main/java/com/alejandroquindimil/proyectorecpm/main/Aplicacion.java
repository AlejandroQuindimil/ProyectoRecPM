package com.alejandroquindimil.proyectorecpm.main;

import java.io.Serializable;

public class Aplicacion implements Serializable {

    private Long id;
    private String name;

    public Aplicacion(int idx) {
        this.id = Long.valueOf(idx);
        this.name = "Aplicacion "+idx;
    }

    public Aplicacion(int idx, String name) {
        this.id = Long.valueOf(idx);
        this.name = name+" "+idx;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
