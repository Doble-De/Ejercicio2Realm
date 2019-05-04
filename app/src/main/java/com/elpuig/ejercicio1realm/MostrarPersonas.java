package com.elpuig.ejercicio1realm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class MostrarPersonas extends AppCompatActivity {

    TextView textView, id;
    String idm, nombre, apellido, genero, edad;
    boolean encontrado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_personas);

        Realm.init(this);

        textView = findViewById(R.id.mostrar);
        id = findViewById(R.id.idm);

        listarPersonas();

        findViewById(R.id.modificar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(idm+" "+nombre+" "+apellido+" "+genero+" "+ edad);
                if (buscarid() != null){
                    Intent intent = new Intent(MostrarPersonas.this, ModifPersonas.class);
                    intent.putExtra("id",idm);
                    intent.putExtra("name",nombre);
                    intent.putExtra("surname",apellido);
                    intent.putExtra("age",edad);
                    intent.putExtra("gender",genero);
                    startActivity(intent);
                }
            }
        });
    }

    public final List<Persona> listarPersonas(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Persona> personas = realm.where(Persona.class).findAll();
        String lista = "";
        for (Persona persona : personas){
            lista += "Id: " + persona.getId() + " Nombre: " + persona.getNombre() + " Apellido: " + persona.getApellido()+ " Edad: " + persona.getEdad() + " Genero: " + persona.getSexo() +"\n";
        }
        textView.setText(lista);
        return personas;
    }

    public Persona buscarid(){
        encontrado = false;
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Persona> personas = realm.where(Persona.class).findAll();
        int temp = Integer.parseInt((id.getText().toString()));
        for (Persona persona : personas){
            if (temp == persona.getId()){
                idm = String.valueOf(persona.getId());
                nombre = persona.getNombre();
                apellido = persona.getApellido();
                edad = String.valueOf(persona.getEdad());
                genero = persona.getSexo();
                return persona;
            }
        }
        return null;
    }

}
