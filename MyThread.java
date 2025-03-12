package com.example.service;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

public class MyThread extends Thread{
    private MediaPlayer player;
    private int size;
    private Messenger messenger;

    public MyThread(Messenger messenger, MediaPlayer player, int size) {
        this.messenger = messenger;
        this.player = player;
        this.size = size;
    }

    @Override
    public void run() {
        while (player.isPlaying()) {
            int current = player.getCurrentPosition();
            Message message = Message.obtain();
            Bundle bundle = new Bundle();
            bundle.putInt("size", size);
            bundle.putInt("current", current);
            message.setData(bundle);
            try {
                messenger.send(message);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
