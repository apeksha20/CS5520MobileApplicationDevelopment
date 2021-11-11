package edu.neu.madsea.apekshaagarwal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;

public class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final TextView titleTextView;
    private final TextView deadlineTextView;
    private final CheckBox checkBox;

    private final Context context;

    private TaskViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        titleTextView = itemView.findViewById(R.id.title);
        deadlineTextView = itemView.findViewById(R.id.deadline);
        checkBox = itemView.findViewById(R.id.checkbox);
        context = itemView.getContext();

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    itemView.setBackgroundColor(Color.YELLOW);
                } else {
                    itemView.setBackgroundColor(Color.WHITE);
                }
                Task task = MainActivity.taskViewModel.getTask(getAbsoluteAdapterPosition());
                task.setCompleted(isChecked);
                MainActivity.taskViewModel.update(task);
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