package com.drapp.appinnovatio.pruebafirebase;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class RegistroProfesor extends AppCompatActivity {

    private EditText Matricula;
    private EditText Nombre;
    private EditText Ap_Pat;
    private EditText Ap_Mat;
    private EditText Correo;
    private EditText Contrasena;

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    DatabaseReference Prueba = ref.child("Prueba");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_profesor);

        Matricula = (EditText) findViewById(R.id.matricula);
        Nombre = (EditText) findViewById(R.id.nombre);
        Ap_Pat = (EditText) findViewById(R.id.ap_pat);
        Ap_Mat = (EditText) findViewById(R.id.ap_mat);
        Correo = (EditText) findViewById(R.id.correo);
        Contrasena = (EditText) findViewById(R.id.contrasena);


    }



    @Override
    public void onStart() {
        super.onStart();

    }


    public void RegistrarMateria (View view) {

        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();

        int dia = today.monthDay;
        int mes = today.month;
        int anio = today.year;
        mes +=1;

        String Fecha = dia + "-" + mes + "-" + anio;

        String NoCtrl = Matricula.getText().toString();
        DatabaseReference noctrl = Prueba.child(NoCtrl);

     /*Hijo de No. de Ctrl*/
        String nombre = Nombre.getText().toString();
        DatabaseReference nombrebd = noctrl.child("Nombre");
        nombrebd.setValue(nombre);
     /*Hijo de No. de Ctrl*/
        String ap_pat = Ap_Pat.getText().toString();
        DatabaseReference ap_patbd = noctrl.child("Ap_Pat");
        ap_patbd.setValue(ap_pat);
     /*Hijo de No. de Ctrl*/
        String ap_mat = Ap_Mat.getText().toString();
        DatabaseReference ap_matbd = noctrl.child("Ap_Mat");
        ap_matbd.setValue(ap_mat);
     /*Hijo de No. de Ctrl*/
        String correo = Correo.getText().toString();
        DatabaseReference correobd = noctrl.child("Correo");
        correobd.setValue(correo);
     /*Hijo de No. de Ctrl*/
        String contrasena = Contrasena.getText().toString();
        DatabaseReference contrasenabd = noctrl.child("Contraseña");
        contrasenabd.setValue(contrasena);
/*
        Intent explicit_intent;
        explicit_intent = new Intent(this, MainActivity.class);
        startActivity(explicit_intent);*/
    }
}
