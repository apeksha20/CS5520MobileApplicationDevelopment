package edu.neu.madsea.apekshaagarwal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private TaskDatasource data = TaskDatasource.getInstance();
    private ArrayAdapter<Task> itemsAdapter;
    private ListView listViewItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //list view
        listViewItems = (ListView)findViewById(R.id.listView);
        listViewItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent newIntent = new Intent(getApplicationContext(), EditTaskActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("position", i);
                newIntent.putExtras(bundle);
                startActivity(newIntent);
            }
        });
        itemsAdapter = new ArrayAdapter(this, R.layout.listview_item, data.getList()) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                LayoutInflater inflater = ((Activity) this.getContext()).getLayoutInflater();
                View rowView = inflater.inflate(R.layout.listview_item, null,true);

                ((CheckBox)rowView.findViewById(R.id.checkbox)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        if( isChecked){
                            rowView.setBackgroundColor(Color.YELLOW);
                        } else {
                            rowView.setBackgroundColor(Color.WHITE);
                        }
                    }
                });
                ((TextView)rowView.findViewById(R.id.title)).setText(data.getTask(position).getTitle());
                String deadline = new SimpleDateFormat("MMM dd, yyyy").format(data.getTask(position).getDeadline());
                ((TextView)rowView.findViewById(R.id.deadline)).setText("By " + deadline);

                return rowView;
            }
        };
        listViewItems.setAdapter(itemsAdapter);
        try {
            Task task = new Task("Complete assignment", "Complete MAD assignment",
                    "High Priority", new Date("10/22/2021"));
            data.addTask(task);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void addTask(View view) {
        Intent newIntent = new Intent(this, NewTaskActivity.class);
        startActivity(newIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        itemsAdapter.notifyDataSetChanged();
    }
}