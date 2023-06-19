package edu.upc.dsa.restproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;

import edu.upc.dsa.restproject.models.UpdateInfo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String SHARED_PREFS = "PROVA";
    public Button BadgesButton;
    public Button InventoryButton;
    Button buttonUpdate;
    EditText name;
    EditText surname;
    EditText email;
    EditText password;
    String idUser;
    Api APIservice;
    ProgressBar progressBar;
    public static final String TEXT1 = "User's name: ";
    public static final String TEXT2 = "User's surname: ";
    public static final String TEXT3 = "User's email: ";
    public static final String TEXT4 = "User's password: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        buttonUpdate = findViewById(R.id.buttonUpdateProfile);
        progressBar = findViewById(R.id.progressBar);
        this.getIdUser();
        this.initializeViews();
    }
    public void saveVariable(UpdateInfo info) {
        SharedPreferences sharedPreferences= getSharedPreferences("info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putString("User email", info.getEmail() + "User password: " + info.getPassword());
        Log.i("Saved ", "User email: " + info.getEmail() + " User password: " + info.getPassword());
        editor.apply();
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEXT1, name.getText().toString());
        editor.putString(TEXT2, surname.getText().toString());
        editor.putString(TEXT3, email.getText().toString());
        editor.putString(TEXT4, password.getText().toString());
        editor.apply();
        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
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
    public void updateprofile(View view) throws IOException {
        this.getIdUser();
        progressBar.setVisibility(View.VISIBLE);
        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        APIservice = RetrofitClient.getInstance().getMyApi();

        UpdateInfo info = new UpdateInfo(idUser,name.getText().toString(),surname.getText().toString(),email.getText().toString(),password.getText().toString());
        Call<UpdateInfo> call = APIservice.update(info);
        call.enqueue(new Callback<UpdateInfo>() {
            @Override
            public void onResponse(Call<UpdateInfo> call, Response<UpdateInfo> response) {
                progressBar.setVisibility(View.INVISIBLE);
                Log.i("PROBLEM","OnResponse");
                switch (response.code()){
                    case 201:
                        saveData();
                        Intent intentRegister = new Intent(ProfileActivity.this, MainActivity.class);
                        UpdateInfo info = response.body();
                        //assert user != null;
                        saveVariable(info);
                        Toast.makeText(ProfileActivity.this,"Update OK", Toast.LENGTH_SHORT).show();
                        ProfileActivity.this.startActivity(intentRegister);
                        break;
                    case 404:
                        Toast.makeText(ProfileActivity.this,"Something went wrong!", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
            @Override
            public void onFailure(Call<UpdateInfo> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Log.i("PROBLEM","OnFailure",t);
                Snackbar snakyfail = Snackbar.make(view, "NETWORK FAILURE", 3000);
                snakyfail.show();
            }
        });
    }



}
