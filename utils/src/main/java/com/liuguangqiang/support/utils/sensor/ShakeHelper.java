package com.liuguangqiang.support.utils.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Vibrator;

/**
 * ShakeHelper
 * <p>
 * Created by Eric on 15/3/3.
 */
public class ShakeHelper implements SensorEventListener {

    private static final float SHAKE_OFFSET = 19.0f;

    private SensorManager sensorManager;
    private Vibrator vibrator;

    private OnShakeListener mShakeListener;

    private long vibrateDuration = 300;
    private boolean enableVibrator = true;
    private boolean enable = true;

    public void setVibrate(boolean enable, long duration) {
        enableVibrator = enable;
        vibrateDuration = duration;
    }

    public void setVibrate(boolean enable) {
        setVibrate(enable, vibrateDuration);
    }

    public ShakeHelper(Context context) {
        init(context);
    }

    public void setOnShakeListener(OnShakeListener listener) {
        mShakeListener = listener;
    }

    private void init(Context context) {
        sensorManager = (SensorManager) context.getSystemService(context.SENSOR_SERVICE);
        vibrator = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
    }

    public void register() {
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), getSensorRateUs());
    }

    public void unregister() {
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
    }

    private int getSensorRateUs() {
        if (Build.VERSION.SDK_INT >= 20) {
            return SensorManager.SENSOR_DELAY_NORMAL;
        } else {
            return SensorManager.SENSOR_DELAY_FASTEST;
        }
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int sensorType = event.sensor.getType();

        if (enable && sensorType == Sensor.TYPE_ACCELEROMETER) {
            float[] values = event.values;
            float x = values[0];
            float y = values[1];
            if (Math.abs(x) > SHAKE_OFFSET || Math.abs(y) > SHAKE_OFFSET) {
                if (enableVibrator)
                    vibrate();

                if (mShakeListener != null)
                    mShakeListener.onShake();
            }
        }
    }

    public void vibrate() {
        vibrator.vibrate(vibrateDuration);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public interface OnShakeListener {
        void onShake();
    }

}
