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
    String idm, nombreCompleto, genero, edad;
    int ide;
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
                System.out.println(idm+" "+nombreCompleto+" "+genero+" "+ edad);
                if (buscarid() != null){
                    Intent intent = new Intent(MostrarPersonas.this, ModifPersonas.class);
                    intent.putExtra("id",idm);
                    intent.putExtra("name",nombreCompleto);
                    intent.putExtra("age",edad);
                    intent.putExtra("gender",genero);
                    startActivity(intent);
                    onDestroy();
                }
            }
        });

        findViewById(R.id.eliminar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ide = buscarid().getId();
                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                RealmResults<Persona> delete = realm.where(Persona.class)
                        .equalTo("id", ide)
                        .findAll();

                delete.deleteAllFromRealm();

                realm.commitTransaction();

                listarPersonas();
            }
        });
    }

    public final List<Persona> listarPersonas(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Persona> personas = realm.where(Persona.class).findAll();
        String lista = "";
        for (Persona persona : personas){
            lista += "Id: " + persona.getId() + " Nombre Apellido: " + persona.getNombreCompleto() +" Edad: " + persona.getEdad() + " Genero: " + persona.getSexo() +"\n";
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
                nombreCompleto = persona.getNombreCompleto();
                edad = String.valueOf(persona.getEdad());
                genero = persona.getSexo();
                return persona;
            }
        }
        return null;
    }

}
