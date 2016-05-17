package bit.jacksct1.sensorfun;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    SensorManager mSensorManager;
    Sensor mLight;
    Sensor mAccelerometer;
    TextView tvOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }

    public class SensorEventHandler implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent event)
        {

            Float dif = Float.valueOf(String.valueOf(0.001));
            Float opac = (event.values[0]) * dif;

            ImageView ivCharles = (ImageView) findViewById(R.id.ivCharles);
            ivCharles.setAlpha(opac);

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy)
        {
        }

    }
    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(new SensorEventHandler(), mLight, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(new SensorEventHandler());
    }


    }
