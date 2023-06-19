package edu.upc.dsa.restproject;

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
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

import edu.upc.dsa.restproject.models.UpdateInfo;
import edu.upc.dsa.restproject.models.UserRegister;
import edu.upc.dsa.restproject.models.idUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateActivity extends AppCompatActivity {
    private static final String SHARED_PREFS = "PROVA";
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

    public void btnClicked(View view) throws IOException {
        if(view== buttonUpdate){
            Intent intentRegister = new Intent(UpdateActivity.this, UpdateActivity.class);
            UpdateActivity.this.startActivity(intentRegister);
        }
    }
    public void returnFunction(View view){
        Intent intentRegister = new Intent(UpdateActivity.this, MainActivity.class);
        UpdateActivity.this.startActivity(intentRegister);
    }
    public void getIdUser(){
        SharedPreferences sharedPreferences = getSharedPreferences("idUser", Context.MODE_PRIVATE);
        this.idUser = sharedPreferences.getString("idUser", null);
    }

    public void updateinfo(View view) throws IOException {
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
                        Intent intentRegister = new Intent(UpdateActivity.this, MainActivity.class);
                        UpdateInfo info = response.body();
                        //assert user != null;
                        saveVariable(info);
                        Toast.makeText(UpdateActivity.this,"Update OK", Toast.LENGTH_SHORT).show();
                        UpdateActivity.this.startActivity(intentRegister);
                        break;
                    case 404:
                        Toast.makeText(UpdateActivity.this,"Something went wrong!", Toast.LENGTH_SHORT).show();
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
