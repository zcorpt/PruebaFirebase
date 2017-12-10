package com.drapp.appinnovatio.pruebafirebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RegistroProfesor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_profesor);
    }

    public void RegistrarMateria (View view) {
        Intent explicit_intent;
        explicit_intent = new Intent(this, MainActivity.class);
        startActivity(explicit_intent);
    }
}
