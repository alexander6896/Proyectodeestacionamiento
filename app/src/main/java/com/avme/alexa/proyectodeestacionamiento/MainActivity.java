package com.avme.alexa.proyectodeestacionamiento;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import OpenHelper.SQLite_OpenHelper;

public class MainActivity extends AppCompatActivity {

    Button iniciarsesion,registro;
    EditText numerocontrol, password;

    SQLite_OpenHelper helper=new SQLite_OpenHelper(this,"BD1",null,1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciarsesion=(Button)findViewById(R.id.button3);
        registro=(Button)findViewById(R.id.button4);
        EditText numerocontrol=(EditText) findViewById(R.id.editText3);
        iniciarsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText numerocontrol=(EditText) findViewById(R.id.editText3);
                EditText password=(EditText) findViewById(R.id.editText5);

                try {
                    Cursor cursor = helper.ConsultarUsuPas(
                            numerocontrol.getText().toString(), password.getText().toString());
                    if(cursor.getCount()>0){
                        Intent iniciarsesion = new Intent(MainActivity.this, GenerarCodigoQr.class);

                        iniciarsesion.putExtra("controlid",numerocontrol.getText().toString());

                        startActivity(iniciarsesion);
                    }else{
                        Toast.makeText(getApplicationContext(),"Usuario y/o Password incorrectos",Toast.LENGTH_LONG).show();
                    }
                    numerocontrol.setText("");
                    password.setText("");
                    numerocontrol.findFocus();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }


        });
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registro=new Intent(MainActivity.this, Registro.class);
                startActivity(registro);
            }
        });
    }


}
