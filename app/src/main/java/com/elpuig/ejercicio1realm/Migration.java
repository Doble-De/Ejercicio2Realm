package com.elpuig.ejercicio1realm;

import android.util.Log;

import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmObject;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

public class Migration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();

        if (oldVersion == 0) {
            Log.d("Migration", "actualitzant a la nova versió" );
            RealmObjectSchema personaSchema = schema.get("Persona");
            personaSchema.addField("nombreCompleto", String.class)
                    .transform(new RealmObjectSchema.Function() {
                        @Override
                        public void apply(DynamicRealmObject obj) {
                            obj.set("nombreCompleto", obj.getString("nombre") + " " + obj.getString("apellido"));
                        }
                    })
                    .removeField("nombre").removeField("apellido");
            oldVersion++;
        }
    }
}
