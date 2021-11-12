package edu.neu.madsea.apekshaagarwal;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;


public class NewTaskActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "REPLY";
    private EditText taskTitle;
    private EditText taskDetail;
    private Spinner tagsSpinner;
    private EditText taskDeadline;
    private TextView errorMessage;
    private CheckBox remindMeCheckBox;
    private EditText remindMeDate;
    private Task task;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        this.task = new Task();
        this.position = -1;

        errorMessage = (TextView) findViewById(R.id.error_new_task);
        errorMessage.setVisibility(View.INVISIBLE);
        taskTitle = (EditText) findViewById(R.id.editTaskTitle);
        taskDetail = (EditText) findViewById(R.id.editTextTaskDetails);
        tagsSpinner = (Spinner) findViewById(R.id.spinnerTags);
        taskDeadline = (EditText) findViewById(R.id.editTextDeadline);
        remindMeCheckBox = (CheckBox) findViewById(R.id.remindMeCheckBox);
        remindMeDate = (EditText) findViewById(R.id.editTextRemindMeDate);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            this.position = bundle.getInt("position", -1);
            if (this.position > -1) {
                this.task = MainActivity.taskViewModel.getTask(position);
                this.taskTitle.setText(task.getTitle());
                this.taskDetail.setText(task.getDetails());
                this.tagsSpinner.setSelection(((ArrayAdapter<String>) tagsSpinner.getAdapter()).getPosition(task.getTag()));
                this.taskDeadline.setText(new SimpleDateFormat("MM/dd/yyyy").format(task.getDeadline()));
                if(task.isRemindMe()){
                this.remindMeDate.setText(new SimpleDateFormat("MM/dd/yyyy").format(task.getRemindMeDate()));
                }
                this.remindMeCheckBox.setChecked(task.isRemindMe());
                this.remindMeDate.setEnabled(task.isRemindMe());
                this.remindMeDate.setCursorVisible(task.isRemindMe());
            }
        }

        remindMeCheckBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                remindMeDate.setEnabled(((CheckBox) v).isChecked());
                remindMeDate.setCursorVisible(((CheckBox) v).isChecked());
            }
        });
    }

    public void saveTask(View view) {
        try {
            if (taskTitle.getText().toString().isEmpty()) {
                throw new IllegalArgumentException("Title cannot be empty");
            }
            task.setTitle(taskTitle.getText().toString());
            task.setDetails(taskDetail.getText().toString());
            task.setTag(tagsSpinner.getSelectedItem().toString());
            task.setDeadline(new SimpleDateFormat("MM/dd/yyyy").parse(taskDeadline.getText().toString()));
            if(remindMeCheckBox.isChecked()){
            task.setRemindMeDate(new SimpleDateFormat("MM/dd/yyyy").parse(remindMeDate.getText().toString()));
            }
            else{
                task.setRemindMeDate(null);
            }
            task.setRemindMe(remindMeCheckBox.isChecked());

            if (position > -1) {
                MainActivity.taskViewModel.update(this.task);
            } else {
                MainActivity.taskViewModel.insert(this.task);
            }
            finish();
        } catch (Exception e) {
            errorMessage.setText(e.getMessage() + "Date is required field");
            errorMessage.setVisibility(View.VISIBLE);
        }
    }

    public void cancelTask(View view) {
        finish();
    }
}