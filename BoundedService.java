package com.example.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BoundedService extends AppCompatActivity {
    private MyApplication application;
    private ProgressBar progressBar;
    private TextView textView2;
    private boolean loop = false;

    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (application.isServiced()) {
                if (msg.what == 0) {
                    Bundle bundle = msg.getData();
                    int size = bundle.getInt("size");
                    int current = bundle.getInt("current");
                    progressBar.setMax(size);
                    progressBar.setProgress(current);

                    String temp = application.getTime(current) + "/" + application.getTime(size);
                    textView2.setText(temp);

                }
            }
        }
    };


    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_started_service);

        application = (MyApplication) getApplication();
        TextView textView1 = findViewById(R.id.textView1);
        textView1.setText("음악 Service(Bounded Service)");
        progressBar = findViewById(R.id.progress);
        textView2 = findViewById(R.id.textView2);

        Intent intent = new Intent(this, MusicBoundedService.class);
        Messenger messenger = new Messenger(handler);
        application.setButton(findViewById(R.id.button));
        application.getButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!application.isServiced()) {
                    intent.putExtra("Messenger", messenger);
                    intent.putExtra("title", "헤어졌다 만났다");
                    intent.putExtra("loop", loop);
                    bindService(intent, connection, BIND_AUTO_CREATE);
                } else {
                    unbindService(connection);
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.isChecked()) {
            loop = false;
        } else {
            loop = true;
        }
        item.setChecked(loop);
        return true;
    }
}