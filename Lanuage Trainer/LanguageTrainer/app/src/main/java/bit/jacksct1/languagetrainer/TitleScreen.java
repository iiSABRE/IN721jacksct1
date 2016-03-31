package bit.jacksct1.languagetrainer;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;


import java.io.Serializable;

//The title screen
public class TitleScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_screen);

        //Set the onClickListener for the entire relative layout of the title activity
        RelativeLayout rLayout = (RelativeLayout) findViewById(R.id.clickable);
        rLayout.setOnClickListener(new clickToBegin());

    }



    //ToMainActivity method changes the current activity to the Question screen
    public void toMainActivity()
    {

        Intent changeActivityIntent = new Intent(this, QuestionScreen.class);
        startActivity(changeActivityIntent);
    }


//onClick handler method that runs the toMainActivity on click
    private class clickToBegin implements View.OnClickListener {
        @Override
        public void onClick(View v)
        {
            toMainActivity();
        }
    }
}
