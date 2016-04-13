package bit.jacksct1.lastfm2016;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        RelativeLayout splash = (RelativeLayout) findViewById(R.id.slashRel);
        splash.setOnClickListener(new listener());
    }

    private class listener implements View.OnClickListener {
        @Override
        public void onClick(View v)
        {
            Intent intent = new Intent(SplashScreen.this, MenuScreen.class);
            startActivity(intent);
        }
    }
}
