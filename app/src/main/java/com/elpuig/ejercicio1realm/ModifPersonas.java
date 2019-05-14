package com.elpuig.ejercicio1realm;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import io.realm.Realm;

public class ModifPersonas extends AppCompatActivity {

    EditText etnombrem, etapellidom, etedadm;
    RadioGroup radioGroup;
    RadioButton rbhombre, rbmujer;
    String id = null, nom = null,  edat = null, genere = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif_personas);

        etnombrem = findViewById(R.id.etnombrem);
        etedadm = findViewById(R.id.etedadm);
        rbhombre = findViewById(R.id.Hombre);
        rbmujer = findViewById(R.id.Mujer);
        radioGroup = findViewById(R.id.radioGroupm);

        final String id = getIntent().getStringExtra("id");
        final String nom = getIntent().getStringExtra("name");
        final String edat = getIntent().getStringExtra("age");
        final String genere = getIntent().getStringExtra("gender");

        System.out.println(id+" "+nom+" "+edat+" "+genere);
        //System.out.println("hola");

        etnombrem.setText(String.valueOf(nom));
        etedadm.setText(edat);
        if (genere.equals("Hombre")){
            rbhombre.setChecked(true);
        }else if (genere.equals("Mujer")){
            rbmujer.setChecked(true);
        }



        Realm.init(this);
        final Realm realm = Realm.getDefaultInstance();

        findViewById(R.id.modif).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                int idm = Integer.parseInt(id);
                int radioButtonID = radioGroup.getCheckedRadioButtonId();
                View radioButton = radioGroup.findViewById(radioButtonID);
                int idx = radioGroup.indexOfChild(radioButton);
                RadioButton r = (RadioButton) radioGroup.getChildAt(idx);
                String text = r.getText().toString();
                realm.beginTransaction();
                Persona addpersona = new Persona(idm, etnombrem.getText().toString(),text, Integer.parseInt((etedadm.getText().toString())));
                //Constructor de persona para sarlo todo como ArrayList
                String temp = radioGroup.getTransitionName();
                realm.insertOrUpdate(addpersona);

                realm.commitTransaction();

                finish();

            }
        });
    }
}
