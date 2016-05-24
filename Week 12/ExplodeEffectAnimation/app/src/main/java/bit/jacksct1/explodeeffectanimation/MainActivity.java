package bit.jacksct1.explodeeffectanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.easyandroidanimations.library.Animation;
import com.easyandroidanimations.library.AnimationListener;
import com.easyandroidanimations.library.ExplodeAnimation;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView ivWesteros = (ImageView) findViewById(R.id.ivWesteos);
        new ExplodeAnimation(ivWesteros)
                .setExplodeMatrix(ExplodeAnimation.MATRIX_2X2)
                .setInterpolator(new DecelerateInterpolator())
                .setDuration(500)
                .setListener(new AnimationListener() {
                    @Override
                    public void onAnimationEnd(Animation animation)
                    {

                    }
                })
                .animate();
    }
}
