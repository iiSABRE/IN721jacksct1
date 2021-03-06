package bit.jacksct1.complexscreencontrolspract22;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import bit.jacksct1.complexscreencontrolspract22.MainActivity;
import bit.jacksct1.complexscreencontrolspract22.R;

/**
 * Created by jacksct1 on 20/03/2016.
 */

//class extending the DialogFragment class
public class DialogFrag extends DialogFragment
{


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        CharSequence selected = getArguments().getCharSequence("instrument");

        //Create builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //set the dialogfragment properties using the builder
        builder.setIcon(R.drawable.music_disk);
        builder.setTitle("Confirm " + selected +  " Classes");
        builder.setPositiveButton("Confirm", new YesButtonHandler());
        builder.setNegativeButton("Cancel", new NoButtonHandler());
        Dialog customDialog = builder.create();

        return customDialog;

    }

    //Yes button handler class. Calls the MainActivity method and sends it true
    public class YesButtonHandler implements DialogInterface.OnClickListener
    {

        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            MainActivity myActivity = (MainActivity) getActivity();
            myActivity.giveMeMyData(true);
        }
    }

    //No button handler class. Calls the MainActivity method and sends it false
    public class NoButtonHandler implements DialogInterface.OnClickListener
    {

        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            MainActivity myActivity = (MainActivity) getActivity();
            myActivity.giveMeMyData(false);
        }
    }
}
