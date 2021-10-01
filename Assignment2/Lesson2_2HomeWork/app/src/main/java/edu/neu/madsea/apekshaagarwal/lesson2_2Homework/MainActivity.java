package edu.neu.madsea.apekshaagarwal.lesson2_2Homework;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int mCount = 0;
    private TextView mShowCount;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount = (TextView) findViewById(R.id.show_count);

        if (savedInstanceState != null) {
            mShowCount.setText(savedInstanceState.getString("saved_counter"));
            mCount = savedInstanceState.getInt("saved_counter_value");
        }
    }

    public void countUp(View view) {
        ++mCount;
        if (mShowCount != null)
            mShowCount.setText(String.format("%s", mCount));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(LOG_TAG, "onSaveInstanceState");
        outState.putString("saved_counter", Integer.toString(mCount));
        outState.putInt("saved_counter_value", mCount);
    }

}


