package edu.upc.dsa.restproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    public Button BadgesButton;
    public Button InventoryButton;
    public String idUser;
    Api APIservice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        this.getIdUser();
        this.initializeViews();
    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.buttonInventory:
                saveidUser(idUser);
                i = new Intent(this, InventoryActivity.class);
                startActivity(i);
                break;
            case R.id.buttonBadges:
                saveidUser(idUser);
                i = new Intent(this,BadgesActivity.class);
                startActivity(i);
                break;
        }
    }

    public void initializeViews() {
        InventoryButton = findViewById(R.id.buttonInventory);
        BadgesButton = findViewById(R.id.buttonBadges);

        BadgesButton.setOnClickListener(this);
        InventoryButton.setOnClickListener(this);
    }

    public void saveVariables(edu.upc.dsa.restproject.models.idUser idUser) {
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
        Intent intentRegister = new Intent(ProfileActivity.this, LoginActivity.class);
        ProfileActivity.this.startActivity(intentRegister);
    }
    public void getIdUser(){
        SharedPreferences sharedPreferences = getSharedPreferences("idUser", Context.MODE_PRIVATE);
        this.idUser = sharedPreferences.getString("idUser", null);
    }



}
