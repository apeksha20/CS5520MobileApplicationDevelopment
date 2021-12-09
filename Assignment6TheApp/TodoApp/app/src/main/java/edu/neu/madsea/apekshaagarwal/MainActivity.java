package edu.neu.madsea.apekshaagarwal;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.WorkManager;

import android.Manifest;
import android.annotation.SuppressLint;

import android.content.Intent;

import android.content.pm.PackageManager;
import android.os.Bundle;

import android.util.Log;
import android.view.View;

import java.util.Collections;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TaskListAdapter taskListAdapter;
    public static TaskViewModel taskViewModel;
    public static WorkManager workManager;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //System.out.println("TIME: " + TimestampConverter.fromDate(new Date()));

        recyclerView = findViewById(R.id.recyclerview);
        taskListAdapter = new TaskListAdapter(new TaskListAdapter.TaskDiff());
        recyclerView.setAdapter(taskListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        taskViewModel.getAllTasks().observe(this, tasks -> {
            // Update the cached copy of the words in the adapter.
            taskListAdapter.submitList(tasks);
        });

        workManager = WorkManager.getInstance(this);

        ItemTouchHelper.Callback drag = new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                return makeMovementFlags(dragFlags, 0);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                if(viewHolder.getItemViewType() != target.getItemViewType()){
                    return false;
                }

                int fromPosition = viewHolder.getAbsoluteAdapterPosition();
                int toPosition = target.getAbsoluteAdapterPosition();
//                reallyMoved(fromPosition, toPosition);
//                Collections.swap(taskViewModel.getAllTasks().getValue(), fromPosition, toPosition);
                taskListAdapter.notifyItemMoved(fromPosition, toPosition);
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            }
        };
        new ItemTouchHelper(drag).attachToRecyclerView(recyclerView);
    }

    public void addTask(View view) {
        Intent newIntent = new Intent(this, NewTaskActivity.class);
        startActivity(newIntent);
    }

}
