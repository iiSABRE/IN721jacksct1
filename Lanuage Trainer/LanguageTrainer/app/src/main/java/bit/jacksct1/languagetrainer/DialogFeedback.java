package bit.jacksct1.languagetrainer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DialogFeedback extends DialogFragment
{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        //Get the correct answer, the right/wrong boolean and also boolean checking if the quiz is over
        CharSequence correct = getArguments().getCharSequence("correct");
        Boolean result = getArguments().getBoolean("Result");
        Boolean end = getArguments().getBoolean("End");

        //Create builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //set the dialogfragment properties using the builder

        //check if their answer was right and display appropriate feedback
        if(result)
        {
            builder.setIcon(R.drawable.correct);
            builder.setTitle("Correct");
        }
        else
        {
            builder.setIcon(R.drawable.incorrect);
            builder.setTitle("Incorrect");
            builder.setMessage("The correct answer was " + correct);
        }

        //check if the quiz is over and set button to results
        if(end)
        {
            builder.setPositiveButton("Results", new ConfirmButtonHandler());
        }
        else
        {
            builder.setPositiveButton("Next Question", new ConfirmButtonHandler());
        }



        Dialog customDialog = builder.create();

        return customDialog;
    }


    //Create questions screen and call its next question method
    private class ConfirmButtonHandler implements DialogInterface.OnClickListener
    {
        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            QuestionScreen myActivity = (QuestionScreen) getActivity();
            myActivity.nextQuestion();
        }
    }
}
