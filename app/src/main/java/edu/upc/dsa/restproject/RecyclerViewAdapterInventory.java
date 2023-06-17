package edu.upc.dsa.restproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.upc.dsa.restproject.models.Insignias;
import edu.upc.dsa.restproject.models.Inventory;

public class RecyclerViewAdapterInventory extends RecyclerView.Adapter<RecyclerViewAdapterInventory.ViewHolder>{
    private static RecyclerClickViewListener listener;
    public List<Inventory> inventory;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView name;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.name);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view){
            listener.recyclerViewListClicked(this.getLayoutPosition());
        }
    }

    public RecyclerViewAdapterInventory(List<Inventory> inventory, RecyclerClickViewListener listener) {
        this.inventory = inventory;
        this.listener =listener;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterInventory.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_inventorycards,parent,false);
        RecyclerViewAdapterInventory.ViewHolder viewHolder= new RecyclerViewAdapterInventory.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterInventory.ViewHolder holder, int position) {
        holder.name.setText(inventory.get(position).getName());
        /*Picasso.get()
                .load("https://res.cloudinary.com/teepublic/image/private/s--Xq_1zPuU--/c_crop,x_10,y_10/c_fit,h_830/c_crop,g_north_west,h_1038,w_1038,x_-154,y_-104/l_upload:v1565806151:production:blanks:vdbwo35fw6qtflw9kezw/fl_layer_apply,g_north_west,x_-265,y_-215/b_rgb:ffffff/c_limit,f_jpg,h_630,q_90,w_630/v1536844414/production/designs/3149437_0.jpg")
                .into(holder.image);*/
    }

    @Override
    public int getItemCount() {
        return inventory.size();
    }
}
