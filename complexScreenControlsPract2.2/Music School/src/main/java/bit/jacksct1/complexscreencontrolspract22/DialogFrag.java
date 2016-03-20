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
public class DialogFrag extends DialogFragment
{


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setIcon(R.drawable.music_disk);
        builder.setTitle("Enrollment Confirmation");
        builder.setPositiveButton("Yes", new YesButtonHandler());
        builder.setNegativeButton("No", new NoButtonHandler());
        Dialog customDialog = builder.create();

        return customDialog;

    }

    public class YesButtonHandler implements DialogInterface.OnClickListener
    {

        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            MainActivity myActivity = (MainActivity) getActivity();
            myActivity.giveMeMyData(true);
        }
    }

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
