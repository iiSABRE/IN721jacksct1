package bit.jacksct1.languagetrainer;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

<<<<<<< HEAD
import java.io.Serializable;

public class TitleScreen extends AppCompatActivity {



=======
public class TitleScreen extends AppCompatActivity {

>>>>>>> origin/master
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_screen);

<<<<<<< HEAD


=======
>>>>>>> origin/master
        //Create handler
        Handler handler = new Handler();
        //Create runnable and generate it run method to call the toMainActivity()
        Runnable runnable = new Runnable() {
            @Override
            public void run()
            {
                toMainActivity();
            }
        };
        //Call handler postDelayed using runable and setting it to 5sec delay
        handler.postDelayed(runnable, 5000);


    }

    //ToMainActivity method chanes the current activity
    public void toMainActivity()
    {
<<<<<<< HEAD

        Intent changeActivityIntent = new Intent(this, QuestionScreen.class);
        startActivity(changeActivityIntent);
    }

    public void createQuestons()
    {

    }
=======
        Intent changeActivityIntent = new Intent(this, QuestionScreen.class);
        startActivity(changeActivityIntent);
    }
>>>>>>> origin/master
}
