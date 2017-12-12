package com.drapp.appinnovatio.pruebafirebase;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class RegistroProfesor extends AppCompatActivity implements View.OnClickListener{

    private EditText Matricula;
    private EditText Nombre;
    private EditText Ap_Pat;
    private EditText Ap_Mat;
    private EditText Correo;
    private EditText Contrasena;

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    DatabaseReference Prueba = ref.child("Prueba");

    EditText edithtextUsuario, edithtextContraseña;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;



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

        edithtextUsuario = Correo;
        edithtextContraseña = Contrasena;
        progressBar = (ProgressBar) findViewById(R.id.progressbar1);
        findViewById(R.id.RegistrarMateria).setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();

       // findViewById(R.id.login2).setOnClickListener(this);


    }



    @Override
    public void onStart() {
        super.onStart();

    }


    public void RegistrarMateria () {

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

    private void registerUser(){
        String usuario = edithtextUsuario.getText().toString().trim();
        String contraseña = edithtextContraseña.getText().toString().trim();

        if(usuario.isEmpty()){
            edithtextUsuario.setError("Correo necesario");
            edithtextUsuario.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(usuario).matches()){
            edithtextUsuario.setError("Por favor introduzca un correo valido");
            edithtextUsuario.requestFocus();
            return;
        }
        if (contraseña.isEmpty()){
            edithtextContraseña.setError("Contraseña necesaria");
            edithtextContraseña.requestFocus();
            return;
        }

        if(contraseña.length()<6){
            edithtextContraseña.setError("El minimo de contrase es de 6 caracteres");
            edithtextContraseña.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(usuario, contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()){
                    Intent intent = new Intent(RegistroProfesor.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_SHORT).show();
                    startActivity(intent);

                } else{

                    if (task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(), "Tu estas registrado", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.RegistrarMateria:
                registerUser();
                RegistrarMateria();

                break;

         /*   case R.id.login2:
                startActivity(new Intent(this, Inicio.class));

                break; */
        }
    }
}
