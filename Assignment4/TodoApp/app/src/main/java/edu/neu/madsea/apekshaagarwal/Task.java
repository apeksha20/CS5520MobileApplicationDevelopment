package edu.neu.madsea.apekshaagarwal;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

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


    public Task(String title, String details, String tag, Date deadline) {
        this.title = title;
        this.details = details;
        this.tag = tag;
        this.deadline = deadline;
        this.isCompleted = false;
    }

    public Task() {
        this.isCompleted = false;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && isCompleted == task.isCompleted && tag.equals(task.tag) && title.equals(task.title) && Objects.equals(details, task.details) && deadline.equals(task.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tag, title, details, deadline, isCompleted);
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
                '}';
    }
}
