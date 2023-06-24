package edu.upc.dsa.restproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import edu.upc.dsa.restproject.models.Insignias;
import edu.upc.dsa.restproject.models.Item;

public class RecyclerViewAdapterBadges extends RecyclerView.Adapter<RecyclerViewAdapterBadges.ViewHolder> {
    private static RecyclerClickViewListener listener;
    public List<Insignias> badges;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView name;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.name);
            image=(ImageView) itemView.findViewById(R.id.badge_imageview);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view){
            listener.recyclerViewListClicked(this.getLayoutPosition());
        }
    }

    public RecyclerViewAdapterBadges(List<Insignias> badges, RecyclerClickViewListener listener) {
        this.badges = badges;
        this.listener =listener;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterBadges.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_badgescard,parent,false);
        ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterBadges.ViewHolder holder, int position) {
        holder.name.setText(badges.get(position).getName());
        Picasso.get().load(badges.get(position).getImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return badges.size();
    }
}
