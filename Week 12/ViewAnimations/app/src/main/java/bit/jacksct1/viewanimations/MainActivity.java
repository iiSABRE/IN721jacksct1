package bit.jacksct1.viewanimations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAnimate = (Button) findViewById(R.id.btnAnimate);
        btnAnimate.setOnClickListener(new AnimateHandler());

    }

    private class AnimateHandler implements View.OnClickListener {
        @Override
        public void onClick(View v)
        {

            YoYo.with(Techniques.StandUp)
                    .duration(700)
                    .playOn(findViewById(R.id.imageView));

        }
    }
}
