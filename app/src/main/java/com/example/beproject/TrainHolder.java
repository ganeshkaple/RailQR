package com.example.beproject;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.beproject.models.Train;

import java.text.MessageFormat;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
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
    /* @BindView(R.id.A1_Class_textview)
     TextView A1ClassTextview;
     @BindView(R.id.A2_Class_textview)
     TextView A2ClassTextview;
     @BindView(R.id.A3_Class_textview)
     TextView A3ClassTextview;
     @BindView(R.id.SL_Class_textview)
     TextView SLClassTextview;
     @BindView(R.id.S2_Class_textview)
     TextView S2ClassTextview;
     @BindView(R.id.E3_Class_textview)
     TextView E3ClassTextview;
     @BindView(R.id.EC_Class_textview)
     TextView ECClassTextview;
     @BindView(R.id.CC_Class_textview)
     TextView CCClassTextview;
     @BindView(R.id.EA_Class_textview)
     TextView EAClassTextview;*/
    /*@BindView(R.id.class_view_id)
    LinearLayout classViewId;*/
    @BindView(R.id.card_view_train_item)
    CardView cardViewTrainItem;

   /* @BindView(R.id.ticket_fare_text_view)
    TextView ticketFareTextView;
    @BindView(R.id.seats_text_view)
    TextView seatsTextView;

    @BindView(R.id.seat_availability_layout)
    LinearLayout seatAvailabilityLayout;

*/

    public TrainHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
       /* A1ClassTextview.setVisibility(View.GONE);
        A2ClassTextview.setVisibility(View.GONE);
        A3ClassTextview.setVisibility(View.GONE);
        SLClassTextview.setVisibility(View.GONE);
        S2ClassTextview.setVisibility(View.GONE);
        E3ClassTextview.setVisibility(View.GONE);
        ECClassTextview.setVisibility(View.GONE);
        CCClassTextview.setVisibility(View.GONE);
        EAClassTextview.setVisibility(View.GONE);*/
    }

    public void setContent(Train train) {
        SourceTextView.setText(train.getFromStation().getName());
        DestinationTextView.setText(train.getToStation().getName());
        trainNameTextView.setText(MessageFormat.format("{0}  ({1})", train.getName(), train.getNumber()));
        TimeofTrain.setText(MessageFormat.format("{0} --> {1}", train.getSrcDepartureTime(), train.getDestArrivalTime()));
   /*     List<Class> classes = train.getClasses();
        if (classes ==null || classes.isEmpty()) return;
        for ( Class classz:classes
             ) {
            Log.d(this.toString(), classz.toString());
            switch (classz.getCode()){
                case  "1A":
                    A1ClassTextview.setVisibility(View.VISIBLE);
                    break;
                case  "2A":
                    A2ClassTextview.setVisibility(View.VISIBLE);
                    break;
                case  "3A":
                    A3ClassTextview.setVisibility(View.VISIBLE);
                    break;
                case  "SL":
                    SLClassTextview.setVisibility(View.VISIBLE);
                    break;
                case  "EC":
                    ECClassTextview.setVisibility(View.VISIBLE);
                    break;
                case  "CC":
                    CCClassTextview.setVisibility(View.VISIBLE);
                    break;
                case  "EA":
                    EAClassTextview.setVisibility(View.VISIBLE);
                break;
                    case  "3E":
                    E3ClassTextview.setVisibility(View.VISIBLE);
                    break;
                case  "2S":
                    S2ClassTextview.setVisibility(View.VISIBLE);
                    break;
            }

        }
*/
    }
/*


    @OnClick(R.id.A1_Class_textview)
    public void onA1ClassTextviewClicked() {
    }

    @OnClick(R.id.A2_Class_textview)
    public void onA2ClassTextviewClicked() {
    }

    @OnClick(R.id.A3_Class_textview)
    public void onA3ClassTextviewClicked() {
    }

    @OnClick(R.id.SL_Class_textview)
    public void onSLClassTextviewClicked() {
    }

    @OnClick(R.id.S2_Class_textview)
    public void onS2ClassTextviewClicked() {
    }

    @OnClick(R.id.E3_Class_textview)
    public void onE3ClassTextviewClicked() {
    }

    @OnClick(R.id.EC_Class_textview)
    public void onECClassTextviewClicked() {
    }

    @OnClick(R.id.CC_Class_textview)
    public void onCCClassTextviewClicked() {
    }

    @OnClick(R.id.EA_Class_textview)
    public void onEAClassTextviewClicked() {
    }
*/
}
