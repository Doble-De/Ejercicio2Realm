package com.elpuig.ejercicio1realm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm.init(this);

        final RealmConfiguration conf = new RealmConfiguration.Builder().name("default.realm").schemaVersion(1).migration(new Migration()).build();
        Realm.setDefaultConfiguration(conf);
        Realm.getInstance(conf);

        findViewById(R.id.añadirp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddPersonas.class));
            }
        });

        findViewById(R.id.mostrar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MostrarPersonas.class));
            }
        });

        findViewById(R.id.buscarmain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BuscarPersonas.class));
            }
        });
    }
}

