RESPUESTAS PREGUNTAS DEL DOCUMENTO

1. Se tendria que añadir :
    buildscript {
        repositories {
            jcenter()
        }
        dependencies {
            classpath "io.realm:realm-gradle-plugin:5.11.0"
        }
    }

   en el build.gradle de app y a continuación en la parte de arriba poner :

    apply plugin: 'realm-android'

   una vez añadidio tendremos que sincronizar para que se añada correctamente
   en el proyecto Realm.io.



2. Para heredar de la classe RealmObject hay que poner junto al nombre de la clase "extends RealmObject":

    public class Persona extends RealmObject

   una vez añadidio no he tenido ningun problema con los elementos que ya estaban creados.


3.  @Required: Se utiliza en Realm para que no permita valores nulos en un campo, las primitivas que aceptan
     esta anotación son: Boolean, Byte, Short, Integer, Long, Float, Double, String, byte [] y Date.
    @Ignore: Se utiliza en el caso de que no se quiera guardar un campo especifico, un caso practico seria si
    la entrada de datos tiene contiene más campos que el modelo a utilizar.
    @PrimaryKey: Se utiliza para marcar un campo como clave principlal, esto facilita en muchos casos las busquedas
    de datos.
    @Index: Es muy similar a las PrimaryKey pero más lento y generando más volumen de peso en almacenamineto.

4.  Para hacer un elemeto PrimaryKey hay que introducir @PrimaryKey delante del mismo. No, Realm no admite claves compuestas.



