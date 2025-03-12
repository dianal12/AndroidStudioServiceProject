package com.example.service;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> list = new ArrayList<>();
        list.add("Started Service");
        list.add("Bounded Service");
        list.add("Intent Service");
        list.add("Foreground Service");
        list.add("Remote Service");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = null;
                switch (i) {
                    case 0 :
                        intent = new Intent(MainActivity.this, StartedService.class);
                        break;
                    case 1 :
                        intent = new Intent(MainActivity.this, BoundedService.class);
                        break;
                    case 2 :
                        intent = new Intent(MainActivity.this, StartedService.class);
                        break;
                    case 3 :
                        intent = new Intent(MainActivity.this, StartedService.class);
                        break;
                    case 4 :
                        intent = new Intent(MainActivity.this, StartedService.class);
                        break;
                }
                startActivity(intent);
            }
        });

    }
}