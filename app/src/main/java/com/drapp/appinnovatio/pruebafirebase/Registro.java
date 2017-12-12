package com.drapp.appinnovatio.pruebafirebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Registro extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }
    public void Validar (View view) {
        Intent explicit_intent;
        explicit_intent = new Intent(this, RegistroProfesor.class);
        startActivity(explicit_intent);
    }

}
