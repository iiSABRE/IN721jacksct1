package bit.jacksct1.introtofragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnImageFragment = (Button) findViewById(R.id.btnImageView);
        ViewImageFragment handler = new ViewImageFragment();
        btnImageFragment.setOnClickListener(handler);
    }

    public class ViewImageFragment implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {
            Fragment imageFragment = new ShowImageFragment();
            FragmentManager fm = getFragmentManager();

            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_container_image, imageFragment);
            ft.commit();
        }
    }
}
