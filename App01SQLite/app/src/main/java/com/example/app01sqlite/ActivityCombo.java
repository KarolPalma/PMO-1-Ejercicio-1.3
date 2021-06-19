package com.example.app01sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import com.example.app01sqlite.tablas.Persona;
import com.example.app01sqlite.transacciones.Transacciones;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ActivityCombo extends AppCompatActivity {

    /*VARIABLES GLOBALES*/
    SQLiteConexion conexion;
    Spinner comboPersonas;
    EditText txtnombres, txtapellidos, txtcorreos;
    ArrayList<String> ListaPersonas;
    ArrayList<Persona> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo);

        conexion = new SQLiteConexion(this, Transacciones.NameDataBase, null, 1);
        comboPersonas = (Spinner) findViewById(R.id.comboPersona);
        txtnombres = (EditText) findViewById(R.id.txtnombres);
        txtapellidos = (EditText) findViewById(R.id.txtapellidos);
        txtcorreos = (EditText) findViewById(R.id.txtcorreos);

        ObtenerListaPersonas();

        ArrayAdapter<CharSequence> adp = new ArrayAdapter(this, android.R.layout.simple_spinner_item, ListaPersonas);
        comboPersonas.setAdapter(adp);

        comboPersonas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
               txtnombres.setText(lista.get(position).getNombre());
               txtapellidos.setText(lista.get(position).getApellido());
               txtcorreos.setText(lista.get(position).getCorreo());
           }

           @Override
            public void onNothingSelected(AdapterView<?> parent){

           }
        });
    }

    private void ObtenerListaPersonas(){
        SQLiteDatabase db = conexion.getReadableDatabase();
        Persona listaPersona = null;
        lista = new ArrayList<Persona>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+Transacciones.tablaPersona, null);

        while (cursor.moveToNext()){
            listaPersona = new Persona();
            listaPersona.setId(cursor.getInt(0));
            listaPersona.setNombre(cursor.getString(1));
            listaPersona.setApellido(cursor.getString(2));
            listaPersona.setEdad(cursor.getInt(3));
            listaPersona.setCorreo(cursor.getString(4));
            listaPersona.setDireccion(cursor.getString(5));

            lista.add(listaPersona);
        }
        fillCombo();
    }

    private void fillCombo(){
        ListaPersonas = new ArrayList<String>();
        for (int i = 0; i < lista.size(); i++){
            ListaPersonas.add(lista.get(i).getId()+" | "+
                    lista.get(i).getNombre()+" "+
                    lista.get(i).getApellido());
        }
    }
}