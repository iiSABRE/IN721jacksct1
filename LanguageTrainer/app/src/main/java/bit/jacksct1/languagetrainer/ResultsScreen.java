package bit.jacksct1.languagetrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultsScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_screen);



        Button exitButton = (Button) findViewById(R.id.btnExit);
        exitButton.setOnClickListener(new exitApp());

        Button playAgainButton = (Button) findViewById(R.id.btnPlayAgain);
        playAgainButton.setOnClickListener(new playAgain());

        Intent launchIntent = getIntent();
        int score = launchIntent.getIntExtra("Score",0);
        int outOf = launchIntent.getIntExtra("outOf",0);

        TextView resultText = (TextView) findViewById(R.id.tvResults);
        resultText.setText(score + "/" + outOf);

    }

    private class exitApp implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {
            System.exit(1);
        }
    }

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
