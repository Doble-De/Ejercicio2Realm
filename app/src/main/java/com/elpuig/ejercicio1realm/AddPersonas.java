package com.elpuig.ejercicio1realm;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import io.realm.Realm;

public class AddPersonas extends AppCompatActivity {

    EditText etnombre, etapellido, etedad;
    RadioGroup radioGroup;
    RadioButton rbhombre, rbmujer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_personas);

        etnombre = findViewById(R.id.etnombre);
        etedad = findViewById(R.id.etedad);
        rbhombre = findViewById(R.id.Hombre);
        rbmujer = findViewById(R.id.Mujer);
        radioGroup = findViewById(R.id.radioGroup);

        Realm.init(this);
        final Realm realm = Realm.getDefaultInstance();

        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                int id = idauto();
                int radioButtonID = radioGroup.getCheckedRadioButtonId();
                View radioButton = radioGroup.findViewById(radioButtonID);
                int idx = radioGroup.indexOfChild(radioButton);
                RadioButton r = (RadioButton) radioGroup.getChildAt(idx);
                String text = r.getText().toString();
                realm.beginTransaction();
                Persona addpersona = new Persona(id, etnombre.getText().toString(), text, Integer.parseInt((etedad.getText().toString())));
                //Constructor de persona para sarlo todo como ArrayList
                String temp = radioGroup.getTransitionName();
                realm.insertOrUpdate(addpersona);

                realm.commitTransaction();

                finish();

            }
        });
    }

    private final static int idauto(){
        Realm realm = Realm.getDefaultInstance();
        Number idold = realm.where(Persona.class).max("id");
        int temp;
        if (idold == null){
            temp = 0;
        } else {
            temp = idold.intValue()+1;
        }
        return temp;
    }
}
