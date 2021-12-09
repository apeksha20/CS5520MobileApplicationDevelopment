package edu.neu.madsea.apekshaagarwal;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;

import android.Manifest;
import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class NewTaskActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "REPLY";
    private static final int RESULT_LOAD_IMAGE = 1;
    private static final int PERMISSIONS_READ_REQUEST_CODE = 2;

    private EditText taskTitle;
    private EditText taskDetail;
    private Spinner tagsSpinner;
    private EditText taskDeadline;
    private TextView errorMessage;
    private CheckBox remindMeCheckBox;
    private EditText remindMeDate;
    private Button uploadImage;
    private ImageView imageView;
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
        uploadImage = (Button) findViewById(R.id.buttonUploadImage);
        uploadImage.setEnabled(true);
        imageView = (ImageView) findViewById(R.id.imageView);

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
                if(task.getBitmapImage() != null){
                    imageView.setImageBitmap(Bitmap.createScaledBitmap(task.getBitmapImage(), 256, 256, false));
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

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = ((BitmapDrawable)((ImageView)view.findViewById(R.id.imageView)).getDrawable()).getBitmap();
//                bitmap.compress(Bitmap.CompressFormat.PNG, 100, );
                String path = MediaStore.Images.Media.insertImage(getApplicationContext().getContentResolver(), bitmap, "My Title", null);
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(path), "image/*");
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                uploadImage.setEnabled(false);
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSIONS_READ_REQUEST_CODE);
            }
        }
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
            if((BitmapDrawable)imageView.getDrawable() != null){
            Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
            task.setBitmapImage(bitmap);
            }
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


    public void uploadImage(View view) {
        Intent i = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMISSIONS_READ_REQUEST_CODE){
            Log.d(TAG, "Request Permission" + grantResults[0]);
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                uploadImage.setEnabled(true);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
            //task.setBitmapImage(bitmap);
            imageView.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 256, 256, false));
        }
    }

//    public void ShareTask(View view) {
//        Intent sendIntent = new Intent();
//        sendIntent.setAction(Intent.ACTION_SEND);
//        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
//        sendIntent.setType("text/plain");
//
//        Intent shareIntent = Intent.createChooser(sendIntent, null);
//        startActivity(shareIntent);
//    }
}