
package sg.edu.rp.c346.id19008424.myapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnTask;
    ListView lv;
    ArrayList<Task> altasks;
    ArrayAdapter<Task> aaTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DBHelper dbTask = new DBHelper(MainActivity.this);
        btnTask = findViewById(R.id.btnTask);
        lv = findViewById(R.id.lv);


        btnTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,AddActivity.class);
                startActivity(i);

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbTask = new DBHelper(MainActivity.this);
        altasks = dbTask.getTasks();
        aaTasks = new ArrayAdapter<Task>(MainActivity.this, android.R.layout.simple_list_item_1,altasks);
        aaTasks.notifyDataSetChanged();
        lv.setAdapter(aaTasks);


    }
}