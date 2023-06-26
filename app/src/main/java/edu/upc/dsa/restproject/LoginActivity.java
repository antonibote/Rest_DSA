package edu.upc.dsa.restproject;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import edu.upc.dsa.restproject.models.idUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    public Button buttonEmpezarPartida;
    public Button buttonVerPartidas;
    public Button shopButton;
    public Button buttonAbuse;
    public Button FAQButton;
    public Button LanguageButton;
    public Button MessageButton;
    public Button ProfileButton;
    public ProgressBar progressBar;
    public String idUser;
    Api APIservice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.getIdUser();
        this.initializeViews();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.buttonEmpezarPartida:
                i = new Intent(this, LoginActivity.class);
                startActivity(i);
                break;
            case R.id.shopButton:
                saveidUser(this.idUser);
                i = new Intent(this, TiendaActivity.class);
                startActivity(i);
                break;
            case R.id.buttonAbuse:
                i = new Intent(this, AbuseActivity.class);
                startActivity(i);
                break;
            case R.id.FAQButton:
                i = new Intent(this, FaqActivity.class);
                startActivity(i);
                break;
            case R.id.LanguageButton:
                i = new Intent(this, LanguageActivity.class);
                startActivity(i);
                break;
            case R.id.MessageButton:
                i = new Intent(this, NotificationsActivity.class);
                startActivity(i);
                break;
            case R.id.buttonProfile:
                saveidUser(idUser);
                i = new Intent(this,ProfileActivity.class);
                startActivity(i);
                break;


        }
    }

    public void initializeViews() {
        buttonEmpezarPartida = findViewById(R.id.buttonEmpezarPartida);
        buttonVerPartidas = findViewById(R.id.buttonVerPartidas);
        shopButton = findViewById(R.id.shopButton);
        buttonAbuse = findViewById(R.id.buttonAbuse);
        progressBar = findViewById(R.id.progressBar);
        FAQButton = findViewById(R.id.FAQButton);
        LanguageButton = findViewById(R.id.LanguageButton);
        MessageButton = findViewById(R.id.MessageButton);
        ProfileButton = findViewById(R.id.buttonProfile);


        buttonEmpezarPartida.setOnClickListener(this);
        buttonVerPartidas.setOnClickListener(this);
        shopButton.setOnClickListener(this);
        buttonAbuse.setOnClickListener(this);
        FAQButton.setOnClickListener(this);
        LanguageButton.setOnClickListener(this);
        MessageButton.setOnClickListener(this);
        ProfileButton.setOnClickListener(this);
    }

    public void saveVariables(idUser idUser) {
        SharedPreferences sharedPreferences = getSharedPreferences("idUser", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("idUser", idUser.getIdUser());
        Log.i("SAVING: ", idUser.getIdUser());
        editor.apply();
    }

    public void saveidUser(String idUser) {
        SharedPreferences sharedPreferences = getSharedPreferences("idUser", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("idUser", idUser);
        Log.i("SAVING: ", idUser);
        editor.apply();
    }

    public void returnFunction(View view){
        Intent intentRegister = new Intent(LoginActivity.this, MainActivity.class);
        LoginActivity.this.startActivity(intentRegister);
    }
    public void getIdUser(){
        SharedPreferences sharedPreferences = getSharedPreferences("idUser", Context.MODE_PRIVATE);
        this.idUser = sharedPreferences.getString("idUser", null);
    }
}
