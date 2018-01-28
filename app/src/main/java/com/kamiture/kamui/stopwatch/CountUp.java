package com.kamiture.kamui.stopwatch;

import android.util.Log;

/**
 * Created by kamui_000 on 2018/01/14.
 */

public class CountUp extends CountUpTimer{
    OnTickListener onTickListener;
    OnFinishListener onFinishListener;

    public CountUp(long interval) {
        super(interval);
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void restart() {
        super.restart();
    }

    @Override
    public void stop() {
        super.stop();
    }

    @Override
    public void reset() {
        super.reset();
    }

    @Override
    public void onTick(long elapsedTime) {
        if (onTickListener != null) onTickListener.onTick(elapsedTime);
    }

    @Override
    public void onFinish(long elapsedTime) {
        if (onFinishListener != null) onFinishListener.onFinish(elapsedTime);
    }

    public void setOnTickListener(OnTickListener onTickListener) {
        this.onTickListener = onTickListener;
    }

    public void setOnFinishListener(OnFinishListener onFinishListener) {
        this.onFinishListener = onFinishListener;
    }

}