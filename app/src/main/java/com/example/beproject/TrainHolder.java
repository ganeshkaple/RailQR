package com.example.beproject;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.beproject.models.Train;

import java.text.MessageFormat;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

class TrainHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.Saturday)
    TextView Saturday;
    @BindView(R.id.sunday)
    TextView sunday;
    @BindView(R.id.tuesday)
    TextView tuesday;
    @BindView(R.id.friday)
    TextView friday;
    @BindView(R.id.thursday)
    TextView thursday;
    @BindView(R.id.Wednesday)
    TextView Wednesday;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.trainNameTextView)
    TextView trainNameTextView;
    @BindView(R.id.SourceTextView)
    TextView SourceTextView;
    @BindView(R.id.DestinationTextView)
    TextView DestinationTextView;
    @BindView(R.id.TimeofTrain)
    TextView TimeofTrain;
    @BindView(R.id.Monday)
    TextView Monday;


    public TrainHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setContent(Train train) {
        SourceTextView.setText(train.getFromStation().getName());
        DestinationTextView.setText(train.getToStation().getName());
        trainNameTextView.setText(MessageFormat.format("{0}  ({1})", train.getName(), train.getNumber()));
        TimeofTrain.setText(MessageFormat.format("{0} --> {1}", train.getSrcDepartureTime(), train.getDestArrivalTime()));

    }
}
