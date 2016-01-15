package orangeboat.voidgame;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;


/**
 * Created by Jay on 1/14/2016.
 */
public class Tilt extends Activity implements SensorEventListener {

    Rect rect = new Rect(100,100,100,100);
    Paint paint = new Paint();
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private float lastX, lastY, lastZ;
    private float deltaX = 0;
    private float deltaY = 0;
    private float deltaZ = 0;

    @Override
    public void onCreate( Bundle savedState){
        super.onCreate(savedState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        paint.setARGB(100,100,100,50);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        deltaX = Math.abs(lastX - event.values[0]);
        deltaY = Math.abs(lastY - event.values[1]);
        deltaZ = Math.abs(lastZ - event.values[2]);
        if (deltaX < 2)
            deltaX = 0;
        if (deltaY < 2)
            deltaY = 0;
        lastX = event.values[0];
        lastY = event.values[1];
        lastZ = event.values[2];
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    public void draw(Canvas canvas){
        if(deltaX>0){
            canvas.drawRect(rect, paint);
        }
    }
}
