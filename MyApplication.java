package com.example.service;

import android.app.Application;
import android.widget.Button;

import java.util.Locale;

public class MyApplication extends Application {
    private boolean serviced = false;
    private int progress = 0;
    private int maxProgress = 0;
    private Button button;

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public int getMaxProgress() {
        return maxProgress;
    }

    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public boolean isServiced() {
        return serviced;
    }

    public void setServiced(boolean serviced) {
        this.serviced = serviced;
    }

    public String getTime(int time) {
        int second = time / 1000;
        int minute = second / 60;
        second %= 60;
        return String.format(Locale.KOREA, "%02d:%02d", minute, second);
    }
}
