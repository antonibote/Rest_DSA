package edu.upc.dsa.restproject;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import edu.upc.dsa.restproject.models.Message;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class NotificationsActivity extends AppCompatActivity implements RecyclerClickViewListener {
    private Api APIservice;
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
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if (response.isSuccessful()) {
                    List<Message> mensajes = response.body();
                    adapterMessage = new RecycleViewAdapterNotifications(mensajes, NotificationsActivity.this);
                    recyclerViewMensajes.setAdapter(adapterMessage);
                } else {

                }
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {

            }
        });
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
