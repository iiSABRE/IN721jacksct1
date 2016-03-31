package bit.jacksct1.languagetrainer;


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


//The Question Screen
public class QuestionScreen extends AppCompatActivity {


    //Implement global variables
    ArrayList<QuestionSetup> questionArray = new ArrayList<QuestionSetup>();
    DialogFeedback dialogFrag;
    UtilityManager manager = new UtilityManager();


    //Setting the score and array position to 0
    public int arrayPos = 0;
    public int correctGuesses = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_screen);

        //Call method to create questions
        initalArraySetup();
        //Pass this array to the utility manager to sort
        manager.sortArray(questionArray);
        //Call method to set activity screen up
        setQuestion();


        //Set listener for confirm button
        Button confirm = (Button) findViewById(R.id.btnConfirmAnswer);
        confirm.setOnClickListener(new resultAndNext());


    }

    //Method that creates an array of questions
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

    //Onclick handler method
    private class resultAndNext implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {

            dialogFrag = new DialogFeedback();

            //Get selected radio button
            RadioGroup articleGroup = (RadioGroup) findViewById(R.id.rgArticle);
            int selectedRadioId = articleGroup.getCheckedRadioButtonId();
            RadioButton selectedRadio = (RadioButton) findViewById(selectedRadioId);

            //If no button is selected display toast to promt user to select button
            if(selectedRadio==null)
            {
                Toast.makeText(getBaseContext(), "Please select an answer", Toast.LENGTH_LONG).show();
            }
            else
            {

                //Get correct result
                CharSequence correct = questionArray.get(arrayPos).getArticle();

                Bundle selectedRadioBundle = new Bundle();

                //Send information on if an anser was correct and implement score
                if ((correct).equals(selectedRadio.getText())) {
                    selectedRadioBundle.putBoolean("Result", true);
                    correctGuesses++;
                } else {
                    selectedRadioBundle.putBoolean("Result", false);
                }

                //Send correct answer
                selectedRadioBundle.putCharSequence("correct", correct);

                //check if at end of array and send end boolean
                if(arrayPos+ 1==questionArray.size())
                {
                    selectedRadioBundle.putBoolean("End", true);
                }


                dialogFrag.setArguments(selectedRadioBundle);

                //Set cancelable to false to prevent clicking outside dialog fragment
                dialogFrag.setCancelable(false);

                FragmentManager fm = getFragmentManager();

                dialogFrag.show(fm, "confirm");
            }
        }
    }

    //Dismiss dialog fragment and check end
    public void nextQuestion()
    {
        //Dismiss dialog window
        dialogFrag.dismiss();
        checkEnd();

    }

    //Set up the next question
    public void setQuestion()
    {

        TextView questionNumber = (TextView) findViewById(R.id.tvQuestionNumber);
        questionNumber.setText(("Question " + String.valueOf(arrayPos + 1)) + " of " + questionArray.size());
        ImageView image = (ImageView) findViewById(R.id.ivQuestionImage);
        image.setBackgroundResource(questionArray.get(arrayPos).getPicture());

        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.rgArticle);
        //Clear radio buttons
        radioGroup.clearCheck();
    }

    //Check if at end of quiz and send to ResultsScreen
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

    //Disable the back button(Astin Tiddy)
    @Override
    public void onBackPressed() { }





}
