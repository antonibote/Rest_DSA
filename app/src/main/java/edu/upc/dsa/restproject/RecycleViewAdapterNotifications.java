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

    public List<Message> messages;
    private static RecyclerClickViewListener listener;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView message;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            message = itemView.findViewById(androidx.appcompat.R.id.message);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.recyclerViewListClicked(this.getLayoutPosition());
        }
    }

    public RecycleViewAdapterNotifications(List<Message> messages, RecyclerClickViewListener listener) {
        this.messages = messages;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_notifications, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.message.setText(messages.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}