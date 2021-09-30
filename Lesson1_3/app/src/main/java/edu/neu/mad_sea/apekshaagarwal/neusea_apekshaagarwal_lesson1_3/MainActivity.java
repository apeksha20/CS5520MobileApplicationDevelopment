package edu.neu.mad_sea.apekshaagarwal.neusea_apekshaagarwal_lesson1_3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int mCount = 0;
    private TextView mShowCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount = (TextView) findViewById(R.id.show_count);
    }

    public void showToast(View view) {
        Toast toast = Toast.makeText(this, R.string.toast_message,
                Toast.LENGTH_SHORT);
        toast.show();
    }

    public void countUp(View view) {
        ++mCount;
        Button counter = (Button) view;
        if (mShowCount != null)
            mShowCount.setText(String.format("%s",mCount));
        if (mCount % 2 == 0) {
            counter.setBackgroundColor(Color.GREEN);
        } else {
            counter.setBackgroundColor(Color.CYAN);
        }
        findViewById(R.id.button_zero).setBackgroundColor(Color.MAGENTA);
    }

    public void defaultToZero(View view) {
        mCount = 0;
        Button counter = (Button) view;
        if (mShowCount != null)
            mShowCount.setText(Integer.toString(mCount));
        counter.setBackgroundColor(Color.GRAY);
    }
}