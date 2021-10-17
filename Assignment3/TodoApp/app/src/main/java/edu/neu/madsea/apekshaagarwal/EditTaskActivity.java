package edu.neu.madsea.apekshaagarwal;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditTaskActivity extends AppCompatActivity {
    private EditText taskTitle;
    private EditText taskDetail;
    private Spinner tagsSpinner;
    private EditText taskDeadline;
    private TextView errorMessage;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        Bundle bundle = getIntent().getExtras();
        this.position = bundle.getInt("position");

        errorMessage = (TextView) findViewById(R.id.error_new_task);
        errorMessage.setVisibility(View.INVISIBLE);

        Task task = TaskDatasource.getInstance().getTask(this.position);

        taskTitle = (EditText) findViewById(R.id.editTaskTitle);
        taskTitle.setText(task.getTitle());
        taskDetail = (EditText) findViewById(R.id.editTextTaskDetails);
        taskDetail.setText(task.getDetails());
        tagsSpinner = (Spinner) findViewById(R.id.spinnerTags);
        tagsSpinner.setSelection(((ArrayAdapter<String>)tagsSpinner.getAdapter()).getPosition(task.getTag()));
        taskDeadline = (EditText) findViewById(R.id.editTextDeadline);
        taskDeadline.setText(new SimpleDateFormat("MM/dd/yyyy").format(task.getDeadline()));
    }

    public void saveTask(View view) {
        Task newTask = null;
        try {
            newTask = new Task(
                    taskTitle.getText().toString(),
                    taskDetail.getText().toString(),
                    tagsSpinner.getSelectedItem().toString(),
                    new SimpleDateFormat("MM/dd/yyyy").parse(taskDeadline.getText().toString()));
            TaskDatasource.getInstance().editTask(newTask, position);
            finish();
        } catch (ParseException e) {
            errorMessage.setText(e.getMessage());
            errorMessage.setVisibility(View.VISIBLE);
        }
    }

    public void cancelTask(View view) {
        finish();
    }
}