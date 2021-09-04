package com.example.shc_learing_app.quizresult;

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

import java.util.List;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreViewAdpater> {

    List<ScoreData> list;
    Context context;



    public ScoreAdapter(List<ScoreData> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public ScoreViewAdpater onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view  = LayoutInflater.from(context).inflate(R.layout.score_list_item,parent,false);
        return new ScoreViewAdpater(view);
    }

//    int k;

    @Override
    public void onBindViewHolder(@NonNull ScoreViewAdpater holder, int position) {

//        k = list.size() -50; //85-30

        ScoreData currentItem = list.get(position); // position+ k
        holder.name.setText(currentItem.getName());
        holder.score.setText(String.valueOf(currentItem.getScore()));
        holder.rank.setText(String.valueOf(list.size()-position)); //(position +k)
        Glide.with(context).load(currentItem.getImage()).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return list.size();


//        return Math.min(list.size(),50);

    }

    public static class ScoreViewAdpater extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name,score,rank;

        public ScoreViewAdpater(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.score_user_image);
            name = itemView.findViewById(R.id.score_user_name);
            score = itemView.findViewById(R.id.score_user_reslt);
            rank = itemView.findViewById(R.id.score_user_rank);

        }
    }
}
