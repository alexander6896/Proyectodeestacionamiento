package OpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//Estructura de la base de datos
public class SQLite_OpenHelper extends SQLiteOpenHelper{


    public SQLite_OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="create table usuarios(_ID integer primary key autoincrement, Nombre text, Carrera text, Semestre text, Placas text," +
                "Nocontrol text, Password text);";
        db.execSQL(query);//Crea la tabla solo una vez
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Metodo que permite abrir la BD
    public void abrir(){
        this.getWritableDatabase();//ESCRBIR EN LA BASE DE DATOS
    }
    //Metodo que permite cerrar la BD
    public void cerrar(){
        this.close();
    }

    //METODO QUE PERMITE INSERTAR REGISTROS EN LA TABLA USUARIOS
    public void insertarReg(String nom, String carr, String sem, String pla, String nocon, String pass){
        ContentValues valores=new ContentValues();
        valores.put("Nombre",nom);
        valores.put("Carrera",carr);
        valores.put("Semestre",sem);
        valores.put("Placas",pla);
        valores.put("Nocontrol",nocon);
        valores.put("Password",pass);
        this.getWritableDatabase().insert("usuarios",null,valores);
    }

    //METODO QUE PERMITE VALIDAR SI EL USUSARIO EXISTE
    public Cursor ConsultarUsuPas(String nocontrol, String pass) throws SQLException{
        Cursor mcursor=null;
        mcursor=this.getReadableDatabase().query("usuarios",new String[]{"_ID",
                "Nombre","Carrera","Semestre","Placas","Nocontrol","Password"},
                "Nocontrol like '"+nocontrol+"' and Password like '"+pass+"'",
                null,null,null,null);
        return mcursor;
    }
}
