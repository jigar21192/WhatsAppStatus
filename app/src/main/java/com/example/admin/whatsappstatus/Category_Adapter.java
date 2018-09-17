package com.example.admin.whatsappstatus;

/**
 * Created by admin on 06-09-2018.
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

class Category_Adapter extends RecyclerView.Adapter<Category_Adapter.MyViewHolder> {
    List<Category_Model>clist;

    public Category_Adapter(List<Category_Model> cmlist) {
        this.clist=cmlist;
    }

    @NonNull
    @Override
    public Category_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,int position) {
        Category_Model cm=clist.get(position);
        holder.category.setText(cm.getCategory());




    }

    @Override
    public int getItemCount() {
        return clist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView category;

        public MyViewHolder(View itemView) {
            super(itemView);
            category=(TextView)itemView.findViewById(R.id.row_item);



        }
    }
}


