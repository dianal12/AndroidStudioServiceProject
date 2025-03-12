package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.Messenger;
import android.widget.Toast;

public class MusicBoundedService extends Service {
    private MyApplication application;
    private MediaPlayer player;

    public MusicBoundedService() {
    }

    @Override
    public void onCreate() {
        application = (MyApplication) getApplication();
    //    super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
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
                application.getButton().performClick();
            }
        });
        MyThread thread = new MyThread(messenger, player, player.getDuration());
        thread.start();
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        application.setServiced(false);
        application.getButton().setText("Music Service 시작");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public void onDestroy() {
        if (player.isPlaying())
            player.stop();
        Toast.makeText(getBaseContext(), "Music Service가 중지", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}