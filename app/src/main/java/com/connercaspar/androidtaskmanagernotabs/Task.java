package com.connercaspar.androidtaskmanagernotabs;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;

@Entity
public class Task {

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

    public Task(String title, String dueDate, String details, boolean isComplete, String completeDate, boolean isPriority) {
        this.title = title;
        this.dueDate = dueDate;
        this.details = details;
        this.isComplete = isComplete;
        this.completeDate = completeDate;
        this.isPriority = isPriority;
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

}
