package com.example.petagram;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Pantalla con la Bio del desarrollador.
 */
public class AcercaDeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.titulo_acerca);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
