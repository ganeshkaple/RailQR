package com.example.beproject.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ganesh on 2/1/18.
 */
public class Owner implements Parcelable {

    public final static Creator<Owner> CREATOR = new Creator<Owner>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Owner createFromParcel(Parcel in) {
            return new Owner(in);
        }

        public Owner[] newArray(int size) {
            return (new Owner[size]);
        }

    };
    @SerializedName("reputation")
    @Expose
    private int reputation;
    @SerializedName("user_id")
    @Expose
    private int userId;
    @SerializedName("user_type")
    @Expose
    private String userType;
    @SerializedName("profile_image")
    @Expose
    private String profileImage;
    @SerializedName("display_name")
    @Expose
    private String displayName;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("accept_rate")
    @Expose
    private int acceptRate;

    protected Owner(Parcel in) {
        this.reputation = ((int) in.readValue((int.class.getClassLoader())));
        this.userId = ((int) in.readValue((int.class.getClassLoader())));
        this.userType = ((String) in.readValue((String.class.getClassLoader())));
        this.profileImage = ((String) in.readValue((String.class.getClassLoader())));
        this.displayName = ((String) in.readValue((String.class.getClassLoader())));
        this.link = ((String) in.readValue((String.class.getClassLoader())));
        this.acceptRate = ((int) in.readValue((int.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public Owner() {
    }

    /**
     * @param profileImage
     * @param link
     * @param userId
     * @param reputation
     * @param displayName
     * @param acceptRate
     * @param userType
     */
    public Owner(int reputation, int userId, String userType, String profileImage, String displayName, String link, int acceptRate) {
        super();
        this.reputation = reputation;
        this.userId = userId;
        this.userType = userType;
        this.profileImage = profileImage;
        this.displayName = displayName;
        this.link = link;
        this.acceptRate = acceptRate;
    }

    public int getReputation() {
        return reputation;
    }

    public void setReputation(int reputation) {
        this.reputation = reputation;
    }

    public Owner withReputation(int reputation) {
        this.reputation = reputation;
        return this;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Owner withUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Owner withUserType(String userType) {
        this.userType = userType;
        return this;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public Owner withProfileImage(String profileImage) {
        this.profileImage = profileImage;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Owner withDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Owner withLink(String link) {
        this.link = link;
        return this;
    }

    public int getAcceptRate() {
        return acceptRate;
    }

    public void setAcceptRate(int acceptRate) {
        this.acceptRate = acceptRate;
    }

    public Owner withAcceptRate(int acceptRate) {
        this.acceptRate = acceptRate;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(reputation);
        dest.writeValue(userId);
        dest.writeValue(userType);
        dest.writeValue(profileImage);
        dest.writeValue(displayName);
        dest.writeValue(link);
        dest.writeValue(acceptRate);
    }

    public int describeContents() {
        return 0;
    }

}
