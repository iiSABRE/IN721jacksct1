package bit.jacksct1.rippleeffectanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.skyfishjy.library.RippleBackground;

public class MainActivity extends AppCompatActivity {

    public boolean ripple = false;
    public RippleBackground rippleBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rippleBackground=(RippleBackground)findViewById(R.id.content);
        ImageView imageView=(ImageView)findViewById(R.id.centerImage);
        imageView.setOnClickListener(new RippleHandler());

        }

    private class RippleHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            if(ripple)
            {
                rippleBackground.stopRippleAnimation();
                ripple = false;
            }
            else
            {
                rippleBackground.startRippleAnimation();
                ripple = true;
            }
        }
    }



}
