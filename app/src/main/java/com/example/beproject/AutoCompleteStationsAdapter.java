package com.example.beproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.beproject.models.Station;
import com.example.beproject.models.StationApiResponse;
import com.example.beproject.models.remote.SOService;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class AutoCompleteStationsAdapter extends BaseAdapter implements Filterable {
    private static final int MAX_RESULTS = 10;
    private final Context context;
    private final PostItemListener itemListener;/*
    public AutoCompleteStationsAdapter(List<Station> items, Context context, PostItemListener itemListener) { ;
        this.items = items;
        this.context = context;
        this.itemListener = itemListener;
    }
*/
    private List<Station> items = new ArrayList<>();
    private SOService service;

    public AutoCompleteStationsAdapter(Context context, SOService service, PostItemListener itemListener) {
        this.context = context;
        this.service = service;
        this.itemListener = itemListener;

    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint != null) {
                    items.clear();
                    service.getStationNames(constraint.toString(), service.API_KEY)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe(new Observer<StationApiResponse>() {
                                @Override
                                public void onCompleted() {
                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onNext(StationApiResponse stationApiResponse) {

                                    List<Station> stations = stationApiResponse.getStations();
                                    items = stations;


                                }
                            });


                    filterResults.values = items;
                    filterResults.count = items.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && (results.count > 0)) {
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };


    }

    public void updateAnswers(List<Station> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public Station getItem(int adapterPosition) {
        return items.get(adapterPosition);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View listView, ViewGroup parent) {
        if (listView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            listView = inflater.inflate(R.layout.auto_suggest_station_view, parent, false);
        }
        /*Log.d(TAG, "Location " + getItem(position).getLocation() + "\n" +
                getItem(position).getPincode() + "\n" +
                getItem(position).getArea() + "\n" +
                " State" + getItem(position).getState());*/
        ((TextView) listView.findViewById(R.id.station_name_text_view)).setText(String.format("%s", getItem(position).getName()));
        ((TextView) listView.findViewById(R.id.station_code_text_view)).setText(String.format("%s", getItem(position).getCode()));


        return listView;


    }

    public interface PostItemListener {
        void onPostClick(String id);
    }


}
