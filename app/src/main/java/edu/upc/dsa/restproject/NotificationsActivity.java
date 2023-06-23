package edu.upc.dsa.restproject;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.List;
import edu.upc.dsa.restproject.models.Message;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class NotificationsActivity extends AppCompatActivity implements RecyclerClickViewListener {
    Api APIservice;
    private RecyclerView recyclerViewMensajes;
    private RecycleViewAdapterNotifications adapterMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        recyclerViewMensajes = findViewById(R.id.my_new_recycler_view);
        recyclerViewMensajes.setLayoutManager(new LinearLayoutManager(this));

        APIservice = RetrofitClient.getInstance().getMyApi();
        Call<List<Message>> call = APIservice.getmessage();
        try {
            adapterMessage = new RecycleViewAdapterNotifications(call.execute().body(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        recyclerViewMensajes.setAdapter(adapterMessage);
    }

    public void returnFunction(View view) {
        Intent intent = new Intent(NotificationsActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void recyclerViewListClicked(int position) {
    }

    @Override
    public void onClick(View view) {

    }
}
