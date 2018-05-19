package com.connercaspar.androidtaskmanagernotabs;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;
import java.util.Date;

@Entity
public class Task implements Parcelable{


    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "due_date")
    private String dueDate;

    @ColumnInfo(name = "details")
    private String details;

    @ColumnInfo(name = "is_complete")
    private boolean isComplete;

    @ColumnInfo(name = "complete_date")
    private String completeDate;

    @ColumnInfo(name = "is_priority")
    private boolean isPriority;

    @ColumnInfo(name = "date_created")
    private Date dateCreated;

    @ColumnInfo(name = "date_completed")
    private Date dateCompleted;

    public Task(String title, String dueDate, String details, boolean isComplete, String completeDate, boolean isPriority, Date dateCreated, Date dateCompleted) {
        this.title = title;
        this.dueDate = dueDate;
        this.details = details;
        this.isComplete = isComplete;
        this.completeDate = completeDate;
        this.isPriority = isPriority;
        this.dateCreated = dateCreated;
        this.dateCompleted = dateCompleted;
    }

    protected Task(Parcel in) {
        id = in.readInt();
        title = in.readString();
        dueDate = in.readString();
        details = in.readString();
        isComplete = in.readByte() != 0;
        completeDate = in.readString();
        isPriority = in.readByte() != 0;
    }


    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public String getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(String completeDate) {
        this.completeDate = completeDate;
    }

    public boolean isPriority() {
        return isPriority;
    }

    public void setPriority(boolean priority) {
        isPriority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(Date dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    @Override
    public int describeContents() {
        return 0;
    }



    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(dueDate);
        dest.writeString(details);
        dest.writeByte((byte) (isComplete ? 1 : 0));
        dest.writeString(completeDate);
        dest.writeByte((byte) (isPriority ? 1 : 0));
    }
}
