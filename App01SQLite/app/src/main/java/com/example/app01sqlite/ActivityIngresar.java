package com.example.app01sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app01sqlite.transacciones.Transacciones;

public class ActivityIngresar extends AppCompatActivity {

    EditText nomb, apell, age, corr, address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar);

        Button boton = (Button) findViewById(R.id.button);
        nomb = (EditText) findViewById(R.id.txtNombre);
        apell = (EditText) findViewById(R.id.txtApellido);
        age = (EditText) findViewById(R.id.txtEdad);
        corr = (EditText) findViewById(R.id.txtCorreo);
        address = (EditText) findViewById(R.id.txtDirección);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AgregarPersona();
            }

        });

    }

    private void AgregarPersona() {
        SQLiteConexion conexion = new SQLiteConexion(getApplicationContext(), Transacciones.NameDataBase, null, 1 );
        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombre, nomb.getText().toString());
        valores.put(Transacciones.apellido, apell.getText().toString());
        valores.put(Transacciones.edad, age.getText().toString());
        valores.put(Transacciones.correo, corr.getText().toString());
        valores.put(Transacciones.direccion, address.getText().toString());
        Long resultado = db.insert(Transacciones.tablaPersona, Transacciones.id, valores);
        Toast.makeText(getApplicationContext(), "Registro Completado", Toast.LENGTH_LONG).show();
        //db.close();

        clearScreen();
    }

    private void clearScreen(){
        nomb.setText("");
        apell.setText("");
        age.setText("");
        corr.setText("");
        address.setText("");
    }
}