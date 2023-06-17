package edu.upc.dsa.restproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.List;

import edu.upc.dsa.restproject.models.Insignias;
import edu.upc.dsa.restproject.models.Inventory;
import retrofit2.Call;

public class InventoryActivity extends AppCompatActivity implements RecyclerClickViewListener  {
    Api APIservice;
    String idUser;
    private RecyclerViewAdapterInventory adapterInventory;
    private RecyclerView recyclerViewInventory;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        this.getIdUser();

        recyclerViewInventory = findViewById(R.id.recyclerviewinventory);
        recyclerViewInventory.setLayoutManager(new LinearLayoutManager(this));

        APIservice = RetrofitClient.getInstance().getMyApi();
        Call<List<Inventory>> call = APIservice.getInventory(this.idUser);
        try {
            adapterInventory = new RecyclerViewAdapterInventory(call.execute().body(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        recyclerViewInventory.setAdapter(adapterInventory);
    }
    public void returnFunction(View view) {
        Intent intent = new Intent(InventoryActivity.this, ProfileActivity.class);
        startActivity(intent);
    }
    public void getIdUser(){
        SharedPreferences sharedPreferences = getSharedPreferences("idUser", Context.MODE_PRIVATE);
        this.idUser = sharedPreferences.getString("idUser", null);
    }
    @Override
    public void recyclerViewListClicked(int position) {

    }

    @Override
    public void onClick(View view) {

    }

}
