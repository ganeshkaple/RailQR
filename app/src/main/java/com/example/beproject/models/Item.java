package com.example.beproject.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ganesh on 2/1/18.
 */
public class Item implements Parcelable {

    public final static Creator<Item> CREATOR = new Creator<Item>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        public Item[] newArray(int size) {
            return (new Item[size]);
        }

    };
    @SerializedName("owner")
    @Expose
    private Owner owner;
    @SerializedName("is_accepted")
    @Expose
    private boolean isAccepted;
    @SerializedName("score")
    @Expose
    private int score;
    @SerializedName("last_activity_date")
    @Expose
    private int lastActivityDate;
    @SerializedName("last_edit_date")
    @Expose
    private int lastEditDate;
    @SerializedName("creation_date")
    @Expose
    private int creationDate;
    @SerializedName("answer_id")
    @Expose
    private int answerId;
    @SerializedName("question_id")
    @Expose
    private int questionId;

    protected Item(Parcel in) {
        this.owner = ((Owner) in.readValue((Owner.class.getClassLoader())));
        this.isAccepted = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.score = ((int) in.readValue((int.class.getClassLoader())));
        this.lastActivityDate = ((int) in.readValue((int.class.getClassLoader())));
        this.lastEditDate = ((int) in.readValue((int.class.getClassLoader())));
        this.creationDate = ((int) in.readValue((int.class.getClassLoader())));
        this.answerId = ((int) in.readValue((int.class.getClassLoader())));
        this.questionId = ((int) in.readValue((int.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public Item() {
    }

    /**
     * @param creationDate
     * @param questionId
     * @param lastActivityDate
     * @param score
     * @param owner
     * @param answerId
     * @param lastEditDate
     * @param isAccepted
     */
    public Item(Owner owner, boolean isAccepted, int score, int lastActivityDate, int lastEditDate, int creationDate, int answerId, int questionId) {
        super();
        this.owner = owner;
        this.isAccepted = isAccepted;
        this.score = score;
        this.lastActivityDate = lastActivityDate;
        this.lastEditDate = lastEditDate;
        this.creationDate = creationDate;
        this.answerId = answerId;
        this.questionId = questionId;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Item withOwner(Owner owner) {
        this.owner = owner;
        return this;
    }

    public boolean isIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(boolean isAccepted) {
        this.isAccepted = isAccepted;
    }

    public Item withIsAccepted(boolean isAccepted) {
        this.isAccepted = isAccepted;
        return this;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Item withScore(int score) {
        this.score = score;
        return this;
    }

    public int getLastActivityDate() {
        return lastActivityDate;
    }

    public void setLastActivityDate(int lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }

    public Item withLastActivityDate(int lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
        return this;
    }

    public int getLastEditDate() {
        return lastEditDate;
    }

    public void setLastEditDate(int lastEditDate) {
        this.lastEditDate = lastEditDate;
    }

    public Item withLastEditDate(int lastEditDate) {
        this.lastEditDate = lastEditDate;
        return this;
    }

    public int getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(int creationDate) {
        this.creationDate = creationDate;
    }

    public Item withCreationDate(int creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public Item withAnswerId(int answerId) {
        this.answerId = answerId;
        return this;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public Item withQuestionId(int questionId) {
        this.questionId = questionId;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(owner);
        dest.writeValue(isAccepted);
        dest.writeValue(score);
        dest.writeValue(lastActivityDate);
        dest.writeValue(lastEditDate);
        dest.writeValue(creationDate);
        dest.writeValue(answerId);
        dest.writeValue(questionId);
    }

    public int describeContents() {
        return 0;
    }

}
