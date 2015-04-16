package com.ifactory.robotics;

/**
 * Created by smilecs on 4/16/15.
 */

        import android.app.Activity;
        import android.content.Context;
        import android.hardware.Sensor;
        import android.hardware.SensorEvent;
        import android.hardware.SensorEventListener;
        import android.hardware.SensorManager;
        import android.os.Bundle;
        import android.widget.TextView;


public class Sensory extends Activity implements SensorEventListener{
    private SensorManager mSensorManager;
    private Sensor MouseMove;
    Double deltax, deltay, deltaz;
    Double lastx, lasty, lastz;
    TextView dx, dy, dz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay);
        dx = (TextView) findViewById(R.id.deltaX);
        dy = (TextView) findViewById(R.id.deltaY);
        dz = (TextView) findViewById(R.id.deltaZ);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        MouseMove = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        //final double alpha = 0.8;
        //alpha * gravity[0]
        deltax = Math.abs(lastx - event.values[0]);
        deltay = Math.abs(lasty - event.values[1]);
        deltaz = Math.abs(lastz - event.values[2]);
        dx.setText(deltax.toString() +"  original value = " + event.values[0]);
        dy.setText(deltay.toString() +"  original value = " + event.values[1]);
        dz.setText(deltaz.toString() +"  original value = " + event.values[2]);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, MouseMove, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}


