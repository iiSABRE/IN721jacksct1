package bit.jacksct1.languagetrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//The results screen
public class ResultsScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_screen);

        //Set buttons onClickListener
        Button exitButton = (Button) findViewById(R.id.btnExit);
        exitButton.setOnClickListener(new exitApp());

        //Set buttons onClickListener
        Button playAgainButton = (Button) findViewById(R.id.btnPlayAgain);
        playAgainButton.setOnClickListener(new playAgain());

        //Get the score and the array size from Intent
        Intent launchIntent = getIntent();
        int score = launchIntent.getIntExtra("Score",0);
        int outOf = launchIntent.getIntExtra("outOf",0);

        //Display the final score and how much it was out of
        TextView resultText = (TextView) findViewById(R.id.tvResults);
        resultText.setText(score + "/" + outOf);

    }

    //exit the system after clicking exit button
    private class exitApp implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {
            System.exit(1);
        }
    }


    //Go back to the title screen when play again is clicked
    private class playAgain implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {
            Intent changeActivityIntent = new Intent(ResultsScreen.this, TitleScreen.class);
            startActivity(changeActivityIntent);
        }
    }
}
