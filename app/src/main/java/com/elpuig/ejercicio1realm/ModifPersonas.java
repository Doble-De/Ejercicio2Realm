package com.elpuig.ejercicio1realm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ModifPersonas extends AppCompatActivity {

    EditText etnombrem, etapellidom, etedadm;
    RadioGroup radioGroup;
    RadioButton rbhombre, rbmujer;
    String id = null, nom = null, cognom = null, edat = null, genere = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif_personas);

        etnombrem = findViewById(R.id.etnombrem);
        etapellidom = findViewById(R.id.etapellidom);
        etedadm = findViewById(R.id.etedadm);
        rbhombre = findViewById(R.id.Hombre);
        rbmujer = findViewById(R.id.Mujer);
        radioGroup = findViewById(R.id.radioGroupm);

        final String id = getIntent().getStringExtra("id");
        final String nom = getIntent().getStringExtra("name");
        final String cognom = getIntent().getStringExtra("surname");
        final String edat = getIntent().getStringExtra("age");
        final String genere = getIntent().getStringExtra("gender");

        System.out.println(id+" "+nom+" "+cognom+" "+edat+" "+genere);
        //System.out.println("hola");

        etnombrem.setText(String.valueOf(nom));
        etapellidom.setText(String.valueOf(cognom));
        etedadm.setText(edat);
        if (genere.equals("Hombre")){
            rbhombre.setChecked(true);
        }else if (genere.equals("Mujer")){
            rbmujer.setChecked(true);
        }
    }
}
