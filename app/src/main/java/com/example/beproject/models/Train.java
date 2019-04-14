
package com.example.beproject.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Train implements Parcelable {

    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("travel_time")
    @Expose
    private String travelTime;
    @SerializedName("src_departure_time")
    @Expose
    private String srcDepartureTime;
    @SerializedName("dest_arrival_time")
    @Expose
    private String destArrivalTime;
    @SerializedName("from_station")
    @Expose
    private Station fromStation;
    @SerializedName("to_station")
    @Expose
    private Station toStation;

    public static final Creator<Train> CREATOR = new Creator<Train>() {
        @Override
        public Train createFromParcel(Parcel in) {
            return new Train(in);
        }

        @Override
        public Train[] newArray(int size) {
            return new Train[size];
        }
    };
    @SerializedName("days")
    @Expose
    private List<Day> days = null;
    @SerializedName("classes")
    @Expose
    private List<Class> classes = null;

    protected Train(Parcel in) {
        number = in.readString();
        name = in.readString();
        travelTime = in.readString();
        srcDepartureTime = in.readString();
        destArrivalTime = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(number);
        dest.writeString(name);
        dest.writeString(travelTime);
        dest.writeString(srcDepartureTime);
        dest.writeString(destArrivalTime);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(String travelTime) {
        this.travelTime = travelTime;
    }

    public String getSrcDepartureTime() {
        return srcDepartureTime;
    }

    public void setSrcDepartureTime(String srcDepartureTime) {
        this.srcDepartureTime = srcDepartureTime;
    }

    public String getDestArrivalTime() {
        return destArrivalTime;
    }

    public void setDestArrivalTime(String destArrivalTime) {
        this.destArrivalTime = destArrivalTime;
    }

    public Station getFromStation() {
        return fromStation;
    }

    public void setFromStation(Station fromStation) {
        this.fromStation = fromStation;
    }

    public Station getToStation() {
        return toStation;
    }

    public void setToStation(Station toStation) {
        this.toStation = toStation;
    }

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }


}

