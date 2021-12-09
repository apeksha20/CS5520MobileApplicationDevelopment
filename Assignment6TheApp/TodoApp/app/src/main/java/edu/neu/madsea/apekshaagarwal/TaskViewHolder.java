package edu.neu.madsea.apekshaagarwal;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.text.SimpleDateFormat;

public class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final TextView titleTextView;
    private final TextView deadlineTextView;
    private final CheckBox checkBox;
    private final ImageButton deleteTaskButton;

    private final Context context;

    private TaskViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        titleTextView = itemView.findViewById(R.id.title);
        deadlineTextView = itemView.findViewById(R.id.deadline);
        checkBox = itemView.findViewById(R.id.checkbox);
        context = itemView.getContext();
        deleteTaskButton = itemView.findViewById(R.id.deleteButton);


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    itemView.setBackgroundColor(Color.rgb(255, 224, 251));
                } else {
                    itemView.setBackgroundColor(Color.WHITE);
                }
                Task task = MainActivity.taskViewModel.getTask(getAbsoluteAdapterPosition());
                task.setCompleted(isChecked);
                MainActivity.taskViewModel.update(task);
            }
        });

        deleteTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                AlertDialog.Builder alert = new AlertDialog.Builder(
//                        context);
//                alert.setTitle("Delete task");
//                alert.setMessage("Are you sure you want to delete the task?");
//                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        //do your work here
//                        Task task = MainActivity.taskViewModel.getTask(getAbsoluteAdapterPosition());
//                        MainActivity.taskViewModel.delete(task);
//                        dialog.dismiss();
//                    }
//                });
//                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//                alert.show();


                new MaterialAlertDialogBuilder(context)
                        .setTitle("Delete Task")
                        .setMessage("Are you sure you want to delete the task?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Task task = MainActivity.taskViewModel.getTask(getAbsoluteAdapterPosition());
                                MainActivity.taskViewModel.delete(task);
                                dialogInterface.dismiss();

                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .show();
            }
        });
    }

    public void bind(Task task) {
        titleTextView.setText(task.getTitle());
        deadlineTextView.setText(new SimpleDateFormat("MMM dd, yyyy").format(task.getDeadline()));
        checkBox.setChecked(task.isCompleted());
    }

    static TaskViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onClick(View view) {
        Intent newIntent = new Intent(this.context, NewTaskActivity.class); // Edit task
        Bundle bundle = new Bundle();
        bundle.putInt("position", getAbsoluteAdapterPosition());
        newIntent.putExtras(bundle);
        context.startActivity(newIntent);
    }
}