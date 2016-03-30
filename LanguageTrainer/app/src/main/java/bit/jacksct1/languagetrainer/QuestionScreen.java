package bit.jacksct1.languagetrainer;

<<<<<<< HEAD
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class QuestionScreen extends AppCompatActivity {

    ArrayList<QuestionSetup> questionArray = new ArrayList<QuestionSetup>();
    DialogFeedback dialogFrag;
    UtilityManager manager = new UtilityManager();

    public int arrayPos = 0;
    public int correctGuesses = 0;

=======
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class QuestionScreen extends AppCompatActivity {

>>>>>>> origin/master
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_screen);
<<<<<<< HEAD


        initalArraySetup();
        manager.sortArray(questionArray);
        setQuestion();

        Button confirm = (Button) findViewById(R.id.btnConfirmAnswer);
        confirm.setOnClickListener(new resultAndNext());


    }

    public void initalArraySetup()
    {

        questionArray.add(new QuestionSetup("Masculine", "Der", "Apfel", "Apple", (R.drawable.der_apfel)));
        questionArray.add(new QuestionSetup("Neutral", "Das", "Auto", "Car", (R.drawable.das_auto)));
        questionArray.add(new QuestionSetup("Masculine", "Der", "Baum", "Tree", (R.drawable.der_baum)));
        questionArray.add(new QuestionSetup("Feminine", "Die", "Ente", "Duck", (R.drawable.die_ente)));
        questionArray.add(new QuestionSetup("Neutral", "Das", "Haus", "House", (R.drawable.das_haus)));
        questionArray.add(new QuestionSetup("Feminine", "Die", "Hexe", "Witch", (R.drawable.die_hexe)));
        questionArray.add(new QuestionSetup("Feminine", "Die", "Kuh", "Cow", (R.drawable.die_kuh)));
        questionArray.add(new QuestionSetup("Feminine", "Die", "Milch", "Milk", (R.drawable.die_milch)));
        questionArray.add(new QuestionSetup("Neutral", "Das", "Schaf", "Sheep", (R.drawable.das_schaf)));
        questionArray.add(new QuestionSetup("Feminine", "Die", "Strasse", "Street", (R.drawable.die_strasse)));
        questionArray.add(new QuestionSetup("Masculine", "Der", "Stuhl", "Chair", (R.drawable.der_stuhl)));


    }

    private class resultAndNext implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {

            dialogFrag = new DialogFeedback();

            RadioGroup articleGroup = (RadioGroup) findViewById(R.id.rgArticle);
            int selectedRadioId = articleGroup.getCheckedRadioButtonId();
            RadioButton selectedRadio = (RadioButton) findViewById(selectedRadioId);

            if(selectedRadio==null)
            {
                Toast.makeText(getBaseContext(), "Please select an answer", Toast.LENGTH_LONG).show();
            }
            else
            {


                CharSequence correct = questionArray.get(arrayPos).getArticle();

                Bundle selectedRadioBundle = new Bundle();

                if ((correct).equals(selectedRadio.getText())) {
                    selectedRadioBundle.putBoolean("Result", true);
                    correctGuesses++;
                } else {
                    selectedRadioBundle.putBoolean("Result", false);
                }

                selectedRadioBundle.putCharSequence("selected", selectedRadio.getText());
                selectedRadioBundle.putCharSequence("correct", correct);
                dialogFrag.setArguments(selectedRadioBundle);


                FragmentManager fm = getFragmentManager();
                dialogFrag.show(fm, "confirm");
            }
        }
    }

    public void nextQuestion()
    {
        //Dismiss dialog window
        dialogFrag.dismiss();
        checkEnd();

    }

    public void setQuestion()
    {

        TextView questionNumber = (TextView) findViewById(R.id.tvQuestionNumber);
        questionNumber.setText((String.valueOf(arrayPos + 1)) + " of " + questionArray.size());
        ImageView image = (ImageView) findViewById(R.id.ivQuestionImage);
        image.setBackgroundResource(questionArray.get(arrayPos).getPicture());
    }

    public void checkEnd()
    {
        arrayPos++;
        if(arrayPos>questionArray.size()-1)
        {
            Intent changeActivityIntent = new Intent(this, ResultsScreen.class);
            changeActivityIntent.putExtra("Score", correctGuesses );
            changeActivityIntent.putExtra("outOf", questionArray.size() );
            startActivity(changeActivityIntent);
        }
        else
        {
            setQuestion();
        }

    }


=======
    }
>>>>>>> origin/master
}
