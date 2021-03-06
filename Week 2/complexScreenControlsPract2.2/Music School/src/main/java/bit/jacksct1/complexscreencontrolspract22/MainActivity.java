package bit.jacksct1.complexscreencontrolspract22;

import android.app.FragmentManager;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    DialogFrag dialogFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     //Set listener to the confirm button
     Button btnConfirm = (Button)findViewById(R.id.btnConfirm);
     ConfirmEnrollment handler = new ConfirmEnrollment();
     btnConfirm.setOnClickListener(handler);

    //Populate question from resource constants
    Resources resourceResolver = getResources();
    TextView questionText = (TextView) findViewById(R.id.tvChooseInstrum);
    questionText.setText("" + resourceResolver.getString(R.string.question));

    //Calling the method to set up the spinner
    spinnerHandler();


    }

    //Class implementing the OnClickListener
    public class ConfirmEnrollment implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {
            //Calling the method to generate the enrollment message (DISABLED)
            //generateEnrollment();

            //Create dialog fragment, create manager then show fragment
            dialogFrag = new DialogFrag();

            RadioGroup instrumentGroup = (RadioGroup) findViewById(R.id.rgInstruments);
            int selectedRadioId = instrumentGroup.getCheckedRadioButtonId();
            RadioButton selectedRadio = (RadioButton) findViewById(selectedRadioId);

            Bundle selectedInstrument = new Bundle();
            selectedInstrument.putCharSequence("instrument", selectedRadio.getText());
            dialogFrag.setArguments(selectedInstrument);

            FragmentManager fm = getFragmentManager();
            dialogFrag.show(fm, "confirm");

        }


    }

    //spinner handling method
    public void spinnerHandler()
    {

        Resources resourceResolver = getResources();
        Spinner monthSpinner = (Spinner) findViewById(R.id.spMonths);
        int layoutID = android.R.layout.simple_spinner_item;

        //Creating array from the resource constants file
        String monthsArray[] = resourceResolver.getStringArray(R.array.MonthsArray);
        //Populating the spinner with the Strings Array
        ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(this, layoutID, monthsArray);
        monthSpinner.setAdapter(monthAdapter);
    }

    public void giveMeMyData(boolean confirmEnrollment)
    {
        //Dismiss dialog window
        dialogFrag.dismiss();
        TextView postConfirm = (TextView) findViewById(R.id.tvPostConfirm);

        //Getting the text from the selected radio button
        RadioGroup instrumentGroup = (RadioGroup) findViewById(R.id.rgInstruments);
        int selectedRadioId = instrumentGroup.getCheckedRadioButtonId();
        RadioButton selectedRadio = (RadioButton) findViewById(selectedRadioId);
        Spinner monthSpinner = (Spinner) findViewById(R.id.spMonths);
        Resources resourceResolver = getResources();

        //If YES was clicked set textview to enrollment text
        if(confirmEnrollment) {
            postConfirm.setText("" + resourceResolver.getString(R.string.enrolledIn) + " " + selectedRadio.getText() + " lessons in " + monthSpinner.getSelectedItem());
        }
        //If NO clicked then set textview to declined
        else
        {
            postConfirm.setText(R.string.declined);
        }


    }

    //Enrollment message handler method
    //Disabled for Dialog Fragment update
    /*
    public void generateEnrollment()
    {
        //Getting the text from the selected radio button
        RadioGroup instrumentGroup = (RadioGroup) findViewById(R.id.rgInstruments);
        int selectedRadioId = instrumentGroup.getCheckedRadioButtonId();
        RadioButton selectedRadio = (RadioButton) findViewById(selectedRadioId);

        Spinner monthSpinner = (Spinner) findViewById(R.id.spMonths);

        //Populating enrollment message with resource constant and selected radio button
        //getting the text from the select spinner
        Resources resourceResolver = getResources();
        TextView tvEnrolled = (TextView) findViewById(R.id.tvEnrolled);
        tvEnrolled.setText("" + resourceResolver.getString(R.string.enrolledIn) + " " + selectedRadio.getText() + " lessons in " + monthSpinner.getSelectedItem());

    }
    */


}
