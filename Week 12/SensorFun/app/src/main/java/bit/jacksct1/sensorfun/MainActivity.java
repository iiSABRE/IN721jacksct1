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
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    SensorManager mSensorManager;
    Sensor mLight;
    Sensor mAccelerometer;
    RelativeLayout rlParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        rlParent = (RelativeLayout) findViewById(R.id.rlLayout);

    }

    public class SensorEventHandler implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent event)
        {
            if (event.sensor.getType() == Sensor.TYPE_LIGHT)
            {
               light(event);
            }

            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
            {
                accelerometer(event);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy)
        {
        }

    }
    @Override
    protected void onResume() {
        super.onResume();
        SensorEventHandler senHand = new SensorEventHandler();
        mSensorManager.registerListener(senHand, mLight, mSensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(senHand, mAccelerometer, mSensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(new SensorEventHandler());

    }

    private void accelerometer(SensorEvent event)
    {

        ImageView ivCharles = (ImageView) findViewById(R.id.ivCharles);
        Float yPos = ivCharles.getY();

        if(event.values[1] > 1)
        {
            if(!(yPos+(ivCharles.getHeight()) > rlParent.getHeight()))
            {
                yPos = yPos + 25;
                ivCharles.setY(yPos);
            }

        }
        if(event.values[1] < 0)
        {
            if(!(yPos < 0))
            {
                yPos = yPos - 25;
                ivCharles.setY(yPos);
            }

        }
    }

    private void light(SensorEvent event)
    {
        Float dif = Float.valueOf(String.valueOf(0.001));
        Float opac = (event.values[0]) * dif;

        ImageView ivCharles = (ImageView) findViewById(R.id.ivCharles);
        ivCharles.setAlpha(opac);
    }


    }
