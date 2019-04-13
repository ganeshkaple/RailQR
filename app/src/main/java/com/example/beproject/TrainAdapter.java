package com.example.beproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.beproject.models.Train;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TrainAdapter extends RecyclerView.Adapter<TrainHolder> {
    private final Context context;
    private List<Train> trainList;

    public TrainAdapter(Context context, List<Train> trainList) {
        this.context = context;
        this.trainList = trainList;
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

    }

    @Override
    public int getItemCount() {
        return trainList.size();
    }

}
