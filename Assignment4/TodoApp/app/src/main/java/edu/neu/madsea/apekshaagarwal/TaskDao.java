package edu.neu.madsea.apekshaagarwal;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import edu.neu.madsea.apekshaagarwal.Task;

@Dao
public interface TaskDao {
    @Insert(onConflict= OnConflictStrategy.REPLACE)
    void insert(Task task);

    @Update
    int update(Task task);

    @Query("SELECT * FROM todo_tasks")
    LiveData<List<Task>> getAllTasks();
//
//    @Query("SELECT * FROM todo_tasks WHERE ROW_NUMBER() = :position LIMIT 1")
//    LiveData<Task> getTask(int position);

    @Query("DELETE FROM todo_tasks")
    void deleteAll();
}