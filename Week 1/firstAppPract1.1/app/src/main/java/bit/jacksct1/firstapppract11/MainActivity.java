package bit.jacksct1.firstapppract11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selectDogBreed();
    }

    public int pickRandom()
    {
        Random rBreed = new Random();
        int pickBreed = rBreed.nextInt(4);
        return pickBreed;
    }

    public void selectDogBreed()
    {
        TextView headerString = (TextView) findViewById(R.id.txtviewHeader);
        String dogBreed = "";

        switch(pickRandom())
        {
            case 0:
                headerString.setText("Poodle");
                break;
            case 1:
                headerString.setText("Labrador");
                break;
            case 2:
                headerString.setText("Shar Pei");
                break;
            case 3:
                headerString.setText("Newfoundland");
                break;

        }
    }





}
