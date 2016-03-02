package bit.jacksct1.complexscreencontrolspract22;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run()
            {
                toMainActivity();
            }
        };
        handler.postDelayed(runnable, 5000);


    }

    public void toMainActivity()
    {
        Intent changeActivityIntent = new Intent(this, MainActivity.class);
        startActivity(changeActivityIntent);
    }

}
