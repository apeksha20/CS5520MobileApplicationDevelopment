package edu.neu.madsea.apekshaagarwal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Task {
    private String tag;
    private String title;
    private String details;
    private Date deadline;

    public Task(String title, String details, String tag, Date deadline) throws ParseException {
        this.title = title;
        this.details = details;
        this.tag = tag;
//        this.deadline = new SimpleDateFormat("MM/dd/yyyy").parse(deadline);
        this.deadline = deadline;
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

    public void setDeadline(Date deadline) throws ParseException {
        this.deadline = deadline;
//        this.deadline = new SimpleDateFormat("MM/dd/yyyy").parse(deadline);
    }

    public String getTitle() {
        return title;
    }

    public String getDetails() {
        return details;
    }

    public Date getDeadline() {
        return this.deadline;
//        return new SimpleDateFormat("MMM dd, yyyy").format(this.deadline);
    }

    public String getTag() {
        return tag;
    }
}
