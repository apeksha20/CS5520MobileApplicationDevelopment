package edu.neu.madsea.apekshaagarwal;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    private TaskRepository mRepository;

    private final LiveData<List<Task>> mAllTasks;

    public TaskViewModel (Application application) {
        super(application);
        mRepository = new TaskRepository(application);
        mAllTasks = mRepository.getAllTask();
    }

    LiveData<List<Task>> getAllTasks() { return mAllTasks; }

    public void insert(Task task) {
        System.out.println("INSIDE TaskViewModel::insert ; " + task);
        mRepository.insert(task);
    }
    public void update(Task task) { mRepository.update(task); }


    public Task getTask(int position) {
        return mRepository.getTask(position);
    }
}
