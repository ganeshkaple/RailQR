
package com.example.beproject.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Train {

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
    @SerializedName("classes")
    @Expose
    private List<java.lang.Class> classes = null;
    @SerializedName("days")
    @Expose
    private List<Day> days = null;

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


    public List<java.lang.Class> getClasses() {
        return classes;
    }

    public void setClasses(List<java.lang.Class> classes) {
        this.classes = classes;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

    public Station getFromStation() {
        return fromStation;
    }

    public Train setFromStation(Station fromStation) {
        this.fromStation = fromStation;
        return this;
    }

    public Station getToStation() {
        return toStation;
    }

    public Train setToStation(Station toStation) {
        this.toStation = toStation;
        return this;
    }
}
