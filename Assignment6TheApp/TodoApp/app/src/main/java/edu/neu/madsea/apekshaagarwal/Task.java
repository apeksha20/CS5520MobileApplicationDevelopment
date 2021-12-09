package edu.neu.madsea.apekshaagarwal;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;

import java.util.Date;

import java.util.Objects;


@Entity(tableName = "todo_tasks")
public class Task implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="id")
    private int id;

    @NonNull
    private String tag;

    @NonNull
    private String title;

    private String details;

    @NonNull
    @TypeConverters({TimestampConverter.class})
    private Date deadline;

    @NonNull
    private boolean isCompleted;

    @NonNull
    private boolean remindMe;

    @TypeConverters({TimestampConverter.class})
    private Date remindMeDate;
    @TypeConverters({BitmapConverter.class})
    private Bitmap bitmapImage;


    public Task(String title, String details, String tag, Date deadline, boolean remindMe, Date remindMeDate) {
        this.id = MainActivity.taskViewModel.getCount() + 1;
        this.title = title;
        this.details = details;
        this.tag = tag;
        this.deadline = deadline;
        this.remindMeDate = remindMeDate;
        this.isCompleted = false;
        this.remindMe = remindMe;
    }

    public Task(String title, String details, String tag, Date deadline) {
        this(title, details, tag, deadline, false, null);
    }


    public Task() {
        this(null, null, null, null, false, null);
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getTitle() {
        return title;
    }

    public String getDetails() {
        return details;
    }

    public Date getDeadline() {
        return deadline;
    }

    public String getTag() {
        return tag;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isRemindMe() {
        return remindMe;
    }

    public void setRemindMe(boolean remindMe) {
        this.remindMe = remindMe;
    }

    public Date getRemindMeDate() {
        return remindMeDate;
    }

    public void setRemindMeDate(Date remindMeDate) {
        this.remindMeDate = remindMeDate;
    }

    public Bitmap getBitmapImage() {
        return bitmapImage;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", tag='" + tag + '\'' +
                ", title='" + title + '\'' +
                ", details='" + details + '\'' +
                ", deadline=" + deadline +
                ", isCompleted=" + isCompleted +
                ", remindMe=" + remindMe +
                ", remindMeDate=" + remindMeDate +
                ", bitmapImage=" + (bitmapImage==null) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && isCompleted == task.isCompleted && remindMe == task.remindMe && tag.equals(task.tag) && title.equals(task.title) && Objects.equals(details, task.details) && deadline.equals(task.deadline) && Objects.equals(remindMeDate, task.remindMeDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tag, title, details, deadline, isCompleted, remindMe, remindMeDate);
    }

    public void setBitmapImage(Bitmap bitmap) {
        this.bitmapImage = bitmap;
    }
}
