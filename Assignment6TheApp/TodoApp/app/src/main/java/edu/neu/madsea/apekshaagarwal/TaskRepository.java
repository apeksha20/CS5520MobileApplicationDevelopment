package edu.neu.madsea.apekshaagarwal;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskRepository {
    private TaskDao mTaskDao;
    private LiveData<List<Task>> mAllTask;

    TaskRepository(Application application) {
        TaskRoomDatabase db = TaskRoomDatabase.getDatabase(application);
        mTaskDao = db.taskDao();
        mAllTask = mTaskDao.getAllTasks();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Task>> getAllTask() {
        return mAllTask;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Task task) {
        TaskRoomDatabase.databaseWriteExecutor.execute(() -> {
            mTaskDao.insert(task);
        });
    }

    void update(Task task){
       TaskRoomDatabase.databaseWriteExecutor.execute(()->{
           mTaskDao.update(task);
       });
    }
    //deleting a task
    void delete(Task task){
        TaskRoomDatabase.databaseWriteExecutor.execute(()->{
            mTaskDao.delete(task);
        });
    }

    public Task getTask(int position) {
        return mAllTask.getValue().get(position);
    }

    public int getCount() {
        if(mAllTask == null || mAllTask.getValue() == null){
            return -1;
        }
        return mAllTask.getValue().size();
    }

    public void update(List<Task> tasks) {
        TaskRoomDatabase.databaseWriteExecutor.execute(()->{
            mTaskDao.update(tasks);
        });
    }
}