package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.Messenger;
import android.widget.Toast;

public class MusicStatedService extends Service {
    private MyApplication application;
    private MediaPlayer player;

    public MusicStatedService() {
    }

    @Override
    public void onCreate() {
        application = (MyApplication) getApplication();
     //   super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        application.setServiced(true);
        application.getButton().setText("Music Service 중지");
        Messenger messenger = intent.getParcelableExtra("Messenger");
        String title = intent.getStringExtra("title");
        boolean loop = intent.getBooleanExtra("loop", false);
        Toast.makeText(this, "Music Service가 시작되었습니다.\n 노래 제목 : " + title, Toast.LENGTH_SHORT).show();
        player = MediaPlayer.create(this, R.raw.davichi);
        player.setLooping(loop);
        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                stopSelf();
            }
        });
        MyThread thread = new MyThread(messenger, player, player.getDuration());
        thread.start();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        player.stop();
        application.setServiced(false);
        application.getButton().setText("Music Service 시작");
        Toast.makeText(this,  "Music Service가 중지되었습니다", Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}