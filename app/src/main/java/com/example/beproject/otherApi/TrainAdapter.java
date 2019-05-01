package com.example.beproject.otherApi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beproject.R;

import java.util.ArrayList;
import java.util.List;

public class TrainAdapter extends RecyclerView.Adapter<TrainHolder> {
    private static int currentPosition = 0;
    private final Context context;
    private final ItemClickListener listener;
    private List<Train> trainList = new ArrayList<>();

    public TrainAdapter(Context context, List<Train> trainList, ItemClickListener listener) {
        this.context = context;
        this.trainList = trainList;
        this.listener = listener;
    }


    public void setTrainList(List<Train> trainList) {
        this.trainList = trainList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TrainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.train_item_view, parent, false);

        return new TrainHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainHolder holder, int position) {
        Train train = trainList.get(position);
        holder.setContent(train);
//        holder.seatAvailabilityLayout.setVisibility(View.GONE);

        //if the position is equals to the item position which is to be expanded
        if (currentPosition == position) {
            //creating an animation
            Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down_animation);

        /*    //toggling visibility
            holder.seatAvailabilityLayout.setVisibility(View.VISIBLE);

            //adding sliding effect
            holder.seatAvailabilityLayout.startAnimation(slideDown);
        */
        }
/*

        holder.S2ClassTextview.setOnClickListener(view -> {

            //getting the position of the item to expand it
            currentPosition = position;

            //reloding the list
            notifyDataSetChanged();
        });
        holder.A1ClassTextview.setOnClickListener(view -> {

            //getting the position of the item to expand it
            currentPosition = position;

            //reloding the list
            notifyDataSetChanged();
        });

        holder.A3ClassTextview.setOnClickListener(view -> {

            //getting the position of the item to expand it
            currentPosition = position;

            //reloding the list
            notifyDataSetChanged();
        });
        holder.A2ClassTextview.setOnClickListener(view -> {

            //getting the position of the item to expand it
            currentPosition = position;

            //reloding the list
            notifyDataSetChanged();
        });
        holder.SLClassTextview.setOnClickListener(view -> {

            //getting the position of the item to expand it
            currentPosition = position;

            //reloding the list
            notifyDataSetChanged();
        });
        holder.EAClassTextview.setOnClickListener(view -> {

            //getting the position of the item to expand it
            currentPosition = position;

            //reloding the list
            notifyDataSetChanged();
        });

        holder.ECClassTextview.setOnClickListener(view -> {

            //getting the position of the item to expand it
            currentPosition = position;

            //reloding the list
            notifyDataSetChanged();
        });
        holder.CCClassTextview.setOnClickListener(view -> {

            //getting the position of the item to expand it
            currentPosition = position;

            //reloding the list
            notifyDataSetChanged();
        });
        holder.E3ClassTextview.setOnClickListener(view -> {

            //getting the position of the item to expand it
            currentPosition = position;

            //reloding the list
            notifyDataSetChanged();
        });

*/

        holder.itemView.setOnClickListener(v -> listener.onItemClick(train));
    }

    @Override
    public int getItemCount() {
        return trainList.size();
    }


}
