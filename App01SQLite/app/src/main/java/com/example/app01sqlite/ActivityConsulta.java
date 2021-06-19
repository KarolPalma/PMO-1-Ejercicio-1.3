package com.example.app01sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app01sqlite.transacciones.Transacciones;

public class ActivityConsulta extends AppCompatActivity {

    SQLiteConexion conexion;
    EditText id, nombres, apellidos, edades, correos, direcciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        //LLAMAR A LA CONEXIÓN DE BD SQLITE
        conexion = new SQLiteConexion(this, Transacciones.NameDataBase, null, 1 );

        Button btnConsultar = (Button) findViewById(R.id.btnConsultar);
        Button btnActualizar = (Button) findViewById(R.id.btnActualizar);
        Button btnEliminar = (Button) findViewById(R.id.btnEliminar);

        id = (EditText) findViewById(R.id.txtcid);
        nombres = (EditText) findViewById(R.id.txtNombres);
        apellidos = (EditText) findViewById(R.id.txtApellidos);
        edades = (EditText) findViewById(R.id.txtEdades);
        correos = (EditText) findViewById(R.id.txtCorreos);
        direcciones = (EditText) findViewById(R.id.txtDirecciones);

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Buscar();
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Actualizar();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Eliminar();
            }
        });


    }

    private void Buscar() {
        SQLiteDatabase db = conexion.getWritableDatabase();

        //PARAMETROS DE CONFIGURACIÓN DE LA SENTENCIA SELECT
        String [] params = {id.getText().toString()}; //PARAMETRO DE LA BÚSQUEDA
        String [] fields = {Transacciones.nombre,
                            Transacciones.apellido,
                            Transacciones.edad,
                            Transacciones.correo,
                            Transacciones.direccion};
        String wherecond = Transacciones.id + "=?";

        try{
            Cursor cdata = db.query(Transacciones.tablaPersona, fields, wherecond, params, null, null, null);
            cdata.moveToFirst();
            nombres.setText(cdata.getString(0));
            apellidos.setText(cdata.getString(1));
            edades.setText(cdata.getString(2));
            correos.setText(cdata.getString(3));
            direcciones.setText(cdata.getString(4));

            Toast.makeText(getApplicationContext(), "Consultado con Exito", Toast.LENGTH_SHORT).show();

        }catch (Exception ex){
            clearScreen();
            Toast.makeText(getApplicationContext(), "Elemento no encontrado", Toast.LENGTH_SHORT).show();
        }
    }

    private void Actualizar() {
        SQLiteDatabase db = conexion.getWritableDatabase();
        String [] params = {id.getText().toString()}; //PARAMETRO DE LA BÚSQUEDA
        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombre, nombres.getText().toString());
        valores.put(Transacciones.apellido, apellidos.getText().toString());
        valores.put(Transacciones.edad, edades.getText().toString());
        valores.put(Transacciones.correo, correos.getText().toString());
        valores.put(Transacciones.direccion, direcciones.getText().toString());

        db.update(Transacciones.tablaPersona, valores, Transacciones.id + "=?", params);
        Toast.makeText(getApplicationContext(), "Dato Actualizado", Toast.LENGTH_SHORT).show();
        clearScreen();

    }

    private void Eliminar() {
        SQLiteDatabase db = conexion.getWritableDatabase();
        String [] params = {id.getText().toString()}; //PARAMETRO DE LA BÚSQUEDA
        String wherecond = Transacciones.id + "=?";
        db.delete(Transacciones.tablaPersona, wherecond, params);
        Toast.makeText(getApplicationContext(), "Dato Eliminado", Toast.LENGTH_SHORT).show();
        clearScreen();

    }

    private void clearScreen(){
        nombres.setText("");
        apellidos.setText("");
        edades.setText("");
        correos.setText("");
        direcciones.setText("");
    }
}