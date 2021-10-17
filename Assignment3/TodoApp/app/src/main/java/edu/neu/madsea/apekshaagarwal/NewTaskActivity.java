package edu.neu.madsea.apekshaagarwal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewTaskActivity extends AppCompatActivity {
    EditText taskTitle;
    EditText taskDetail;
    Spinner tagsSpinner;
    EditText taskDeadline;
    TextView errorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        errorMessage = (TextView) findViewById(R.id.error_new_task);
        errorMessage.setVisibility(View.INVISIBLE);

        taskTitle = (EditText) findViewById(R.id.editTaskTitle);
        taskDetail = (EditText) findViewById(R.id.editTextTaskDetails);
        tagsSpinner = (Spinner) findViewById(R.id.spinnerTags);
        taskDeadline = (EditText) findViewById(R.id.editTextDeadline);
    }

    public void saveTask(View view) {
        Task newTask = null;
        try {
            newTask = new Task(
                    taskTitle.getText().toString(),
                    taskDetail.getText().toString(),
                    tagsSpinner.getSelectedItem().toString(),
                    new SimpleDateFormat("MM/dd/yyyy").parse(taskDeadline.getText().toString()));
            TaskDatasource.getInstance().addTask(newTask);
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