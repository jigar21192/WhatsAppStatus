package com.example.admin.whatsappstatus;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Status_Adapter extends RecyclerView.Adapter<Status_Adapter.ViewHolder> {
    List<Status_Model>models;
    Context context;

    public Status_Adapter(List<Status_Model> models) {
        this.models=models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Status_Model cm=models.get(position);
        holder.status.setText(cm.getStatus());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView status;
        public ViewHolder(View itemView) {
            super(itemView);

            status=(TextView)itemView.findViewById(R.id.row_item);
        }
    }
}
