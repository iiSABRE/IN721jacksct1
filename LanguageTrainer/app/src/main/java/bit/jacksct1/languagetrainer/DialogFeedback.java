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
        CharSequence selected = getArguments().getCharSequence("selected");
        CharSequence correct = getArguments().getCharSequence("correct");
        Boolean result = getArguments().getBoolean("Result");

        //Create builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //set the dialogfragment properties using the builder

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



        builder.setPositiveButton("Next Question", new ConfirmButtonHandler());

        Dialog customDialog = builder.create();

        return customDialog;
    }

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
