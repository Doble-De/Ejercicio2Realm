package com.elpuig.ejercicio1realm;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Persona extends RealmObject {
    @PrimaryKey
    private int id;
    @Required private String nombre;
    private String apellido;
    private int edad;
    private String sexo;

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }

    public String getSexo() {
        return sexo;
    }

    public Persona(int id, String nombre, String apellido, String sexo, int edad){
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.edad = edad;
    }

    public Persona(){

    }
}
