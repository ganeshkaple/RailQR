package com.example.beproject.otherApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TrainWrapper {

    @SerializedName("ResponseCode")
    @Expose
    private String responseCode;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("TotalTrains")
    @Expose
    private String totalTrains;
    @SerializedName("Trains")
    @Expose
    private List<Train> trains = new ArrayList<>();
    @SerializedName("Message")
    @Expose
    private String message;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalTrains() {
        return totalTrains;
    }

    public void setTotalTrains(String totalTrains) {
        this.totalTrains = totalTrains;
    }

    public List<Train> getTrains() {
        return trains;
    }

    public void setTrains(List<Train> trains) {
        this.trains = trains;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
