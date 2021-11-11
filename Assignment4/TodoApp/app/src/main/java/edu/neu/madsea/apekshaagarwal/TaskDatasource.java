package edu.neu.madsea.apekshaagarwal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskDatasource {
    private ArrayList<Task> tasks;
    private TaskDatasource(){
        tasks = new ArrayList<>();
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public void removeTask(){

    }

    public void editTask(Task newTask, int i) throws ParseException {
        Task oldTask = tasks.get(i);
        oldTask.setTitle(newTask.getTitle());
        oldTask.setDetails(newTask.getDetails());
        oldTask.setTag(newTask.getTag());
        oldTask.setDeadline(newTask.getDeadline());
    }

    public Task getTask(int position){
        return tasks.get(position);
    }

    public List<Task> getList(){
        return tasks;
    }

    private static TaskDatasource data = new TaskDatasource();
    public static TaskDatasource getInstance() {
        return data;
    }
}
