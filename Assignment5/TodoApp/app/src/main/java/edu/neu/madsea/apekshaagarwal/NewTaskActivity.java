package edu.neu.madsea.apekshaagarwal;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


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
                this.remindMeCheckBox.setChecked(task.isRemindMe());
                remindMeDate.setVisibility(task.isRemindMe()?View.VISIBLE:View.INVISIBLE);
                this.remindMeDate.setCursorVisible(task.isRemindMe());
                if (task.isRemindMe()) {
                    this.remindMeDate.setText(new SimpleDateFormat("MM/dd/yyyy HH:mm").format(task.getRemindMeDate()));
                }
            }
        }

        remindMeCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                remindMeDate.setEnabled(((CheckBox) v).isChecked());
                remindMeDate.setVisibility(((CheckBox) v).isChecked()?View.VISIBLE:View.INVISIBLE);
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
            task.setRemindMe(remindMeCheckBox.isChecked());
            if (remindMeCheckBox.isChecked()) {
                task.setRemindMeDate(new SimpleDateFormat("MM/dd/yyyy HH:mm").parse(remindMeDate.getText().toString()));
            } else {
                task.setRemindMeDate(null);
            }

            if (position > -1) {
                MainActivity.taskViewModel.update(this.task);
            } else {
                MainActivity.taskViewModel.insert(this.task);
            }
            if(remindMeCheckBox.isChecked()){
            createNotification(task);
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

    public void createNotification(Task task) {
        String workTag = Integer.toString(task.getId());
        Date remindMeDate = task.getRemindMeDate();
        long delay = remindMeDate.getTime() - new Date().getTime();
        Log.d(TAG, "Setting notification with delay of (seconds): "+ Long.toString(delay));

        MainActivity.workManager.cancelAllWorkByTag(workTag);
        OneTimeWorkRequest request =
                new OneTimeWorkRequest.Builder(NotificationWorker.class)
                        .setInputData(createNotificationData(task))
                        .setInitialDelay(delay, TimeUnit.MILLISECONDS)
                        .addTag(workTag)
                        .build();

        MainActivity.workManager.enqueue(request);
    }

    private Data createNotificationData(Task task) {
        Data.Builder builder = new Data.Builder();
        builder.putString(NotificationWorker.TITLE, task.getTitle());
        builder.putString(NotificationWorker.DETAILS, task.getDetails());
        builder.putInt(NotificationWorker.ID, task.getId());
        return builder.build();
    }


}