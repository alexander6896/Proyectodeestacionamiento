package com.avme.alexa.proyectodeestacionamiento;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import OpenHelper.SQLite_OpenHelper;

public class Registro extends AppCompatActivity {

    Button btnGrabarUsu;
    EditText txtNombre, txtCarrera, txtSemestre, txtPlacas, txtNoControl, txtPassword;

    SQLite_OpenHelper helper=new SQLite_OpenHelper(this,"BD1",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        btnGrabarUsu=(Button)findViewById(R.id.registro);
        txtNombre=(EditText)findViewById(R.id.editText7);
        txtCarrera=(EditText)findViewById(R.id.editText8);
        txtSemestre=(EditText)findViewById(R.id.editText9);
        txtPlacas=(EditText)findViewById(R.id.editText10);
        txtNoControl=(EditText)findViewById(R.id.editText4);
        txtPassword=(EditText)findViewById(R.id.editText2);

        btnGrabarUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.abrir();
                helper.insertarReg(String.valueOf(txtNombre.getText()),
                        String.valueOf(txtCarrera.getText()),
                        String.valueOf(txtSemestre.getText()),
                        String.valueOf(txtPlacas.getText()),
                        String.valueOf(txtNoControl.getText()),
                        String.valueOf(txtPassword.getText()));
                helper.cerrar();
                Toast.makeText(getApplicationContext(),"Registro Almacenado con Exito"
                ,Toast.LENGTH_LONG).show();

                /*Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);*/

                Intent inicio=new Intent(Registro.this, MainActivity.class);
                startActivity(inicio);
            }
        });
    }


}
