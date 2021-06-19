package com.example.app01sqlite;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.app01sqlite.tablas.Persona;
import com.example.app01sqlite.transacciones.Transacciones;

import java.util.ArrayList;

public class ActivityListView extends AppCompatActivity {

    //VARIABLES GLOBALES DE LA ACTIVIDAD
    SQLiteConexion conexion;
    ListView listapersonas;
    ArrayList<Persona> lista;
    ArrayList<String> arregloPersona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        conexion = new SQLiteConexion(this, Transacciones.NameDataBase, null, 1);
        listapersonas = (ListView) findViewById(R.id.lstPersonas);

        ObtenerListaPersonas();

        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arregloPersona);
        listapersonas.setAdapter(adp);

        listapersonas.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                String informacion = "ID" + lista.get(position).getId()+"\n";
                informacion += "Nombre: " + lista.get(position).getNombre();
                Toast.makeText(getApplicationContext(), informacion, Toast.LENGTH_LONG).show();
                Intent Compartir = new Intent();
                Compartir.setAction(Intent.ACTION_SEND);
                Compartir.putExtra(Intent.EXTRA_TEXT, informacion);
                Compartir.setType("text/plain");

                Intent Share = Intent.createChooser(Compartir, null);
                startActivity(Share);

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
        fillList();
    }

    private void fillList(){
        arregloPersona = new ArrayList<String>();
        for(int i = 0; i<lista.size(); i++){
            arregloPersona.add(lista.get(i).getId()+" | "+
                               lista.get(i).getNombre()+" "+
                               lista.get(i).getApellido());
        }

    }
}