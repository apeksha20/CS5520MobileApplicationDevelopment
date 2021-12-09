package edu.neu.madsea.apekshaagarwal;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    private TaskRepository mRepository;

    public TaskViewModel (Application application) {
        super(application);
        mRepository = new TaskRepository(application);
    }

    LiveData<List<Task>> getAllTasks() { return mRepository.getAllTask(); }

    public void insert(Task task) {
        mRepository.insert(task);
    }
    public void update(Task task) { mRepository.update(task); }

    public void update(List<Task> tasks){
        mRepository.update(tasks);
    }

    //delete task
    public void delete(Task task){
        mRepository.delete(task);
    }

    public Task getTask(int position) {
        return mRepository.getTask(position);
    }

    public int getCount() {
        return mRepository.getCount();
    }
}
