package com.drapp.appinnovatio.pruebafirebase;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class RecuperarContrasena extends AppCompatActivity implements View.OnClickListener{

    ProgressBar progress;
    Button bEnviar;

    Session session = null;
    ProgressDialog pdialog = null;
    Context context = null;

    AutoCompleteTextView email,expediente;
    String correo,asunto,mensaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contrasena);

        context = this;
        bEnviar=(Button)findViewById(R.id.ButtonEnviar);
        bEnviar.setOnClickListener(this);
        email = (AutoCompleteTextView)findViewById(R.id.email_recuperar_pass);
        expediente = (AutoCompleteTextView)findViewById(R.id.pass_recuperar_pass);
    }

    @Override
    public void onClick(View v) {

        //acept();

        //Sección del Aleatorio
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();

        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();


        correo = email.getText().toString();
        asunto = expediente.getText().toString()+" Recuperación de contraseña";
        mensaje = "Su nueva contraseña temporal es: " + saltStr + "<br><br><br><br> No responda a este correo," +
                " ya que solo es un servidor que le ayuda a recuperar su contraseña";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("dr.appbyai@gmail.com", "DoctorApp");
            }
        });

        pdialog = ProgressDialog.show(context, "", " Enviando Correo...", true);

        RetreiveFeedTask task = new RetreiveFeedTask();
        task.execute();
    }


    class RetreiveFeedTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            try{
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("dr.appbyai@gmail.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correo));
                message.setSubject(asunto);
                message.setContent(mensaje, "text/html; charset=utf-8");
                Transport.send(message);
            } catch(MessagingException e) {
                e.printStackTrace();
            } catch(Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            pdialog.dismiss();
            email.setText("");

            expediente.setText("");
            Toast.makeText(getApplicationContext(), "Correo enviado", Toast.LENGTH_LONG).show();
            onBackPressed();
        }
    }
}
