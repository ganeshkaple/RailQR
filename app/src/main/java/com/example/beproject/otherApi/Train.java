package com.example.beproject.otherApi;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Train implements Parcelable {

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
    @SerializedName("TrainNo")
    @Expose
    private String trainNo;
    @SerializedName("TrainName")
    @Expose
    private String trainName;
    @SerializedName("Source")
    @Expose
    private String source;
    @SerializedName("ArrivalTime")
    @Expose
    private String arrivalTime;
    @SerializedName("Destination")
    @Expose
    private String destination;
    @SerializedName("DepartureTime")
    @Expose
    private String departureTime;
    @SerializedName("TravelTime")
    @Expose
    private String travelTime;
    @SerializedName("TrainType")
    @Expose
    private String trainType;

    protected Train(Parcel in) {
        trainNo = in.readString();
        trainName = in.readString();
        source = in.readString();
        arrivalTime = in.readString();
        destination = in.readString();
        departureTime = in.readString();
        travelTime = in.readString();
        trainType = in.readString();
    }

    public Train() {
    }

    public Train(String trainNo, String trainName, String source, String arrivalTime, String destination, String departureTime, String travelTime, String trainType) {
        this.trainNo = trainNo;
        this.trainName = trainName;
        this.source = source;
        this.arrivalTime = arrivalTime;
        this.destination = destination;
        this.departureTime = departureTime;
        this.travelTime = travelTime;
        this.trainType = trainType;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(trainNo);
        dest.writeString(trainName);
        dest.writeString(source);
        dest.writeString(arrivalTime);
        dest.writeString(destination);
        dest.writeString(departureTime);
        dest.writeString(travelTime);
        dest.writeString(trainType);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(String travelTime) {
        this.travelTime = travelTime;
    }

    public String getTrainType() {
        return trainType;
    }

    public void setTrainType(String trainType) {
        this.trainType = trainType;
    }


}
