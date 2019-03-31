package com.example.beproject.models;

/**
 * Created by ganesh on 2/1/18.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SOAnswerResponse implements Parcelable {

    public final static Creator<SOAnswerResponse> CREATOR = new Creator<SOAnswerResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SOAnswerResponse createFromParcel(Parcel in) {
            return new SOAnswerResponse(in);
        }

        public SOAnswerResponse[] newArray(int size) {
            return (new SOAnswerResponse[size]);
        }

    };
    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    @SerializedName("has_more")
    @Expose
    private boolean hasMore;
    @SerializedName("quota_max")
    @Expose
    private int quotaMax;
    @SerializedName("quota_remaining")
    @Expose
    private int quotaRemaining;

    protected SOAnswerResponse(Parcel in) {
        in.readList(this.items, (Item.class.getClassLoader()));
        this.hasMore = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.quotaMax = ((int) in.readValue((int.class.getClassLoader())));
        this.quotaRemaining = ((int) in.readValue((int.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public SOAnswerResponse() {
    }

    /**
     * @param hasMore
     * @param quotaMax
     * @param items
     * @param quotaRemaining
     */
    public SOAnswerResponse(List<Item> items, boolean hasMore, int quotaMax, int quotaRemaining) {
        super();
        this.items = items;
        this.hasMore = hasMore;
        this.quotaMax = quotaMax;
        this.quotaRemaining = quotaRemaining;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public SOAnswerResponse withItems(List<Item> items) {
        this.items = items;
        return this;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public SOAnswerResponse withHasMore(boolean hasMore) {
        this.hasMore = hasMore;
        return this;
    }

    public int getQuotaMax() {
        return quotaMax;
    }

    public void setQuotaMax(int quotaMax) {
        this.quotaMax = quotaMax;
    }

    public SOAnswerResponse withQuotaMax(int quotaMax) {
        this.quotaMax = quotaMax;
        return this;
    }

    public int getQuotaRemaining() {
        return quotaRemaining;
    }

    public void setQuotaRemaining(int quotaRemaining) {
        this.quotaRemaining = quotaRemaining;
    }

    public SOAnswerResponse withQuotaRemaining(int quotaRemaining) {
        this.quotaRemaining = quotaRemaining;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(items);
        dest.writeValue(hasMore);
        dest.writeValue(quotaMax);
        dest.writeValue(quotaRemaining);
    }

    public int describeContents() {
        return 0;
    }

}