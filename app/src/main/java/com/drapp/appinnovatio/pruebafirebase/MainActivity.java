package com.drapp.appinnovatio.pruebafirebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Registar (View view) {
        Intent explicit_intent;
        explicit_intent = new Intent(this, Registro.class);
        startActivity(explicit_intent);
    }

    public void Aceptar (View view) {
        Intent explicit_intent;
        explicit_intent = new Intent(this, LectorQR.class);
        startActivity(explicit_intent);
    }
}
