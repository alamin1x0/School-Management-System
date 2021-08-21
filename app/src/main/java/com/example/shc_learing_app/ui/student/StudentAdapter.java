package com.example.shc_learing_app.ui.student;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shc_learing_app.R;
import com.example.shc_learing_app.studentlist.StudentListData;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewAdapter> {
    private List<StudentData> list;
    private Context context;

    public StudentAdapter(List<StudentData> list, Context context) {
        this.list = list;
        this.context = context;
    }



    @NonNull
    @Override
    public StudentAdapter.StudentViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.stuent_item_layout, parent, false);
        return new StudentViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.StudentViewAdapter holder, int position) {

        StudentData item = list.get(position);
        holder.name.setText(item.getName());
        holder.phone.setText(item.getPhone());
        holder.address.setText(item.getAddress());

        try {
            Glide.with(context).load(item.getImage()).placeholder(R.drawable.pro).into(holder.imageView);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class StudentViewAdapter extends RecyclerView.ViewHolder {

        private TextView name, phone, address;
        private ImageView imageView;

        public StudentViewAdapter(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.studentName);
            phone = itemView.findViewById(R.id.studentPhone);
            address = itemView.findViewById(R.id.studentAddress);
            imageView = itemView.findViewById(R.id.studentImage);
        }
    }
}
