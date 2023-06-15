package edu.upc.dsa.restproject;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import android.view.View;
import android.widget.TextView;

import edu.upc.dsa.restproject.models.Message;

public class RecycleViewAdapterNotifications extends RecyclerView.Adapter<RecycleViewAdapterNotifications.ViewHolder> {
    private List<Message> mensajes;
    private RecyclerClickViewListener clickListener;

    public RecycleViewAdapterNotifications(List<Message> mensajes, RecyclerClickViewListener clickListener) {
        this.mensajes = mensajes;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Message message = mensajes.get(position);
        holder.textViewMessage.setText(message.getMessage());
    }

    @Override
    public int getItemCount() {
        return mensajes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textViewMessage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewMessage = itemView.findViewById(android.R.id.text1);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                clickListener.recyclerViewListClicked(position);
            }
        }
    }
}