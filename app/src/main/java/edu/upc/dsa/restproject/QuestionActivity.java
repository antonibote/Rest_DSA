package edu.upc.dsa.restproject;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import edu.upc.dsa.restproject.models.Question;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionActivity extends AppCompatActivity{
    Api APIservice;
    String idUser;
    TextInputEditText message,title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_activity);
        getUserIdFromPreviousActivity();
    }
    public void sendButton(View view) {
        message = findViewById(R.id.messageInput);
        title = findViewById(R.id.titleInput);
        if(!message.getText().toString().equals("")&&!title.getText().toString().equals("")){
            String currentTime = getDate();
            Question q = new Question(currentTime, title.getText().toString(),message.getText().toString(),this.idUser);
            APIservice = RetrofitClient.getInstance().getMyApi();
            Call<Void> call = APIservice.addQuestion(q);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    switch (response.code()){
                        case 201:
                            Snackbar snaky201 = Snackbar.make(view, "Correct abuse report!", 3000);
                            snaky201.show();

                            break;
                        case 403:
                            Snackbar snaky403 = Snackbar.make(view, "Error reporting an abuse", 3000);
                            snaky403.show();
                            break;
                        case 500:
                            Snackbar snaky500 = Snackbar.make(view, "Please fill in the informers text!", 3000);
                            snaky500.show();
                            break;
                    }
                }
                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Snackbar snakyfail = Snackbar.make(view, "NETWORK FAILURE", 3000);
                    snakyfail.show();
                }
            });
        }
    }
    public String getDate(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = c.getTime();
        String currentDate = dateFormat.format(date);
        return currentDate;
    }
    private String getUserIdFromPreviousActivity() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("idUser")) {
            return intent.getStringExtra("idUser").toString();
        } else {
            // Manejar el caso cuando no se encuentra el extra "idUser"
            // Por ejemplo, mostrar un mensaje de error o redirigir a otra actividad
            return null;
        }
    }

    public void Return(View view) {
        Intent intentDashBoard = new Intent(QuestionActivity.this, FaqActivity.class);
        QuestionActivity.this.startActivity(intentDashBoard);
    }





}
