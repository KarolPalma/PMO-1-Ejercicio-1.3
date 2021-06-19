package com.example.app01sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        Button btnSpinner = (Button) findViewById(R.id.btnSpinner);
        btnSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityCombo.class);
                startActivity(intent);
            }
        });*/

        //FOTO
        /*Button btnFoto = (Button) findViewById(R.id.btnFoto);

        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityFoto.class);
                startActivity(intent);
            }
        });*/
    }

    public void clickPrimera(View view) {
        Intent pantalla = new Intent(this, ActivityIngresar.class);
        startActivity(pantalla);
    }

    public void clickSegunda(View view) {
        Intent pantalla = new Intent(this, ActivityConsulta.class);
        startActivity(pantalla);
    }

    public void clickTercera(View view) {
        Intent pantalla = new Intent(this, ActivityListView.class);
        startActivity(pantalla);
    }

    public void clickCuarta(View view) {
        Intent pantalla = new Intent(this, ActivityCombo.class);
        startActivity(pantalla);
    }

    public void clickMapa(View view) {
        Intent pantalla = new Intent(this, MapsActivity.class);
        startActivity(pantalla);
    }

    public void clickFoto(View view) {
        /*Intent intent = new Intent(getApplicationContext(), ActivityFoto.class);
        startActivity(intent);*/
    }
}