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
        setContentView(R.layout.activity_main_tablet);

        //Get reference to Image Button
        Button btnImageFragment = (Button) findViewById(R.id.btnImageView);
        //Create handler
        ViewImageFragment imageHandler = new ViewImageFragment();
        //Set the buttons onClickListener by passing in the handler
        btnImageFragment.setOnClickListener(imageHandler);


        //Get reference to Image Button
        Button btnListFragment = (Button) findViewById(R.id.btnListView);
        //Create handler
        ViewListFragment listHandler = new ViewListFragment();
        //Set the buttons onClickListener by passing in the handler
        btnListFragment.setOnClickListener(listHandler);
    }

    //Create class to implement onClickListener
    public class ViewImageFragment implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {

            //Instantiate the fragment
            Fragment imageFragment = new ShowImageFragment();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_container_image, imageFragment);
            ft.commit();
        }
    }

    //Create class to implement onClickListener
    public class ViewListFragment implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {

            //Instantiate the fragment 
            Fragment listFragment = new ShowListFragment();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_container_list,listFragment);
            ft.commit();
        }
    }
}
