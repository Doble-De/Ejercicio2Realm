package com.elpuig.ejercicio1realm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class BuscarPersonas extends AppCompatActivity {

    TextView busqueda, titulo, tituloc1;
    EditText valor1, valor2;
    RadioButton consulta1, consulta2, consulta3, consulta4, bhombre, bmujer;
    RadioGroup consultas, grupgenro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_personas);

        busqueda = findViewById(R.id.lista);
        consultas = findViewById(R.id.rgconsultas);
        consulta1 = findViewById(R.id.rbrango);
        consulta2 = findViewById(R.id.rbmayor);
        consulta3 = findViewById(R.id.rbmenor);
        consulta4 = findViewById(R.id.rbgenero);
        titulo = findViewById(R.id.tvdesc);
        tituloc1 = findViewById(R.id.tvdesc2);
        valor1 = findViewById(R.id.etvalor1);
        valor2 = findViewById(R.id.etvalor2);
        bhombre = findViewById(R.id.rbgenero1);
        bmujer = findViewById(R.id.rbgenero2);
        grupgenro = findViewById(R.id.rggenerob);
        Realm.init(this);

        findViewById(R.id.rbrango).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccion();
            }
        });

        findViewById(R.id.rbmayor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccion();
            }
        });

        findViewById(R.id.rbmenor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccion();
            }
        });

        findViewById(R.id.rbgenero).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccion();
            }
        });

        findViewById(R.id.buscarb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (consulta1.isChecked()){
                    consulta1();
                }else if (consulta2.isChecked()){
                    consulta2();
                }else if (consulta3.isChecked()){
                    consulta3();
                }else if (consulta4.isChecked()){
                    consulta4();
                }
            }
        });


    }

    public void seleccion(){
        if (consultas.getCheckedRadioButtonId() == consulta1.getId()){
            titulo.setText("Menor Edad");
            valor1.setHint("Edad");
            valor1.setVisibility(View.VISIBLE);
            tituloc1.setVisibility(View.VISIBLE);
            valor2.setVisibility(View.VISIBLE);
            grupgenro.setVisibility(View.INVISIBLE);

        }else if (consultas.getCheckedRadioButtonId() == consulta2.getId()){
            titulo.setText("Intoduce la edad");
            valor1.setHint("Edad");
            valor1.setVisibility(View.VISIBLE);
            tituloc1.setVisibility(View.INVISIBLE);
            valor2.setVisibility(View.INVISIBLE);
            grupgenro.setVisibility(View.INVISIBLE);
        } else if (consultas.getCheckedRadioButtonId() == consulta3.getId()){
            titulo.setText("Intoduce la edad");
            valor1.setHint("Edad");
            valor1.setVisibility(View.VISIBLE);
            tituloc1.setVisibility(View.INVISIBLE);
            valor2.setVisibility(View.INVISIBLE);
            grupgenro.setVisibility(View.INVISIBLE);
        } else if (consultas.getCheckedRadioButtonId() == consulta4.getId()){
            titulo.setText("Selecciona el genero");
            valor1.setVisibility(View.INVISIBLE);
            tituloc1.setVisibility(View.INVISIBLE);
            valor2.setVisibility(View.INVISIBLE);
            grupgenro.setVisibility(View.VISIBLE);
        }
    }


    public final List<Persona> consulta1(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Persona> personas = realm.where(Persona.class).findAll();
        int edad1 = Integer.parseInt(valor1.getText().toString());
        int edad2 = Integer.parseInt(valor2.getText().toString());
        String lista = "";
        for (Persona persona : personas){
            if (persona.getEdad() >= edad1 && persona.getEdad() <= edad2){
                lista += "Id: " + persona.getId() + " Nombre: " + persona.getNombre() + " Apellido: " + persona.getApellido()+ " Edad: " + persona.getEdad() + " Genero: " + persona.getSexo() +"\n";
            }
        }
        if (lista.equals("")){
            lista = "Sin Registros";
        }
        busqueda.setText(lista);
        return personas;
    }

    public final List<Persona> consulta2(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Persona> personas = realm.where(Persona.class).findAll();
        int edad1 = Integer.parseInt(valor1.getText().toString());
        String lista = "";
        for (Persona persona : personas){
            if (persona.getEdad() >= edad1){
                lista += "Id: " + persona.getId() + " Nombre: " + persona.getNombre() + " Apellido: " + persona.getApellido()+ " Edad: " + persona.getEdad() + " Genero: " + persona.getSexo() +"\n";
            }
        }
        if (lista.equals("")){
            lista = "Sin Registros";
        }
        busqueda.setText(lista);
        return personas;
    }

    public final List<Persona> consulta3(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Persona> personas = realm.where(Persona.class).findAll();
        int edad1 = Integer.parseInt(valor1.getText().toString());
        String lista = "";
        for (Persona persona : personas){
            if (persona.getEdad() <= edad1){
                lista += "Id: " + persona.getId() + " Nombre: " + persona.getNombre() + " Apellido: " + persona.getApellido()+ " Edad: " + persona.getEdad() + " Genero: " + persona.getSexo() +"\n";
            }
        }
        if (lista.equals("")){
            lista = "Sin Registros";
        }
        busqueda.setText(lista);
        return personas;
    }

    public final List<Persona> consulta4(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Persona> personas = realm.where(Persona.class).findAll();
        int radioButtonID = grupgenro.getCheckedRadioButtonId();
        View radioButton = grupgenro.findViewById(radioButtonID);
        int idx = grupgenro.indexOfChild(radioButton);
        RadioButton r = (RadioButton) grupgenro.getChildAt(idx);
        String text = r.getText().toString();
        String lista = "";
        for (Persona persona : personas){
            if (persona.getSexo().equals(text)){
                lista += "Id: " + persona.getId() + " Nombre: " + persona.getNombre() + " Apellido: " + persona.getApellido()+ " Edad: " + persona.getEdad() + " Genero: " + persona.getSexo() +"\n";
            }
        }
        if (lista.equals("")){
            lista = "Sin Registros";
        }
        busqueda.setText(lista);
        return personas;
    }
}
