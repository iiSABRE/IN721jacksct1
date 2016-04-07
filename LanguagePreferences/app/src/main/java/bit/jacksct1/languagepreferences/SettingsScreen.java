package bit.jacksct1.languagepreferences;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import static android.graphics.Color.parseColor;

//Settings Screen
public class SettingsScreen extends AppCompatActivity {

    //Global variables
    SharedPreferences prefs;
    SharedPreferences.Editor prefsEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_screen);

        //Get and set buttons onClickListener
        Button btnSetPref = (Button)  findViewById(R.id.btnSetPreferences);
        btnSetPref.setOnClickListener(new preferencesBtnHandler());

        //Get shared preferences
        prefs = getSharedPreferences("prefsDemo", MODE_PRIVATE);
        prefsEditor = prefs.edit();

        //Retrieve the lanuage and colour from shared preferences
        String languagePreference = prefs.getString("language", null);
        String colourPreference = prefs.getString("colour", null);

        //Check if a language is set. Call the setLang method is there is a pref language
        if(languagePreference != null)
        {
            setLang(languagePreference);
        }

        //Set up the spinner by getting string array from resources
        Spinner colourSpinner = (Spinner) findViewById(R.id.spnColour);
        int layoutID = android.R.layout.simple_spinner_item;
        Resources res = getResources();
        String[] coloursArr = res.getStringArray(R.array.selectColourArray);
        ArrayAdapter<String> colourAdapter = new ArrayAdapter<String>(this, layoutID, coloursArr);
        colourSpinner.setAdapter(colourAdapter);

        //Check if a colour is set. Call the setColour method is there is a pref colour
        if(colourPreference != null) {

            setColour(colourPreference);
            //Set the default spinner positon calling the getSpinnerPos method
            colourSpinner.setSelection(getSpinnerPos(colourPreference));

        }
    }

    //Button handler class
    private class preferencesBtnHandler implements View.OnClickListener {
        @Override
        public void onClick(View v)
        {
            //Get the text from the selected radio button
            RadioGroup rg = (RadioGroup) findViewById(R.id.rgLanguage);
            int checkedID = rg.getCheckedRadioButtonId();
            RadioButton checkedButton = (RadioButton)findViewById(checkedID);
            String checkLang = checkedButton.getText().toString();

            //Set the SharedPreferences for language to the selected radio button text
            prefsEditor.putString("language", checkLang);

            //Get the selected spinner item
            Spinner spinner = (Spinner) findViewById(R.id.spnColour);
            String colour = spinner.getSelectedItem().toString();

            //Set the SharedPreferences for colour to the selected spinner text
            prefsEditor.putString("colour", colour);
            //Commit the shared preferences
            prefsEditor.commit();

            //Call the setLang method and pass it the selected language
            setLang(checkLang);
            //Call the setColour method and pss it the selected colour
            setColour(colour);



        }
    }

    //setColour method sets the colour of the TextView using the selected colour
    private void setColour(String colour)
    {

        TextView colHead = (TextView) findViewById(R.id.tvChosenOrNo);

        //Change the colour of the TextView depending on the text of the passed in String
        switch (colour)
        {
            case("Black"):
                colHead.setTextColor(parseColor("black"));
                break;
            case("Red"):
                colHead.setTextColor(parseColor("red"));
                break;
            case("Green"):
                colHead.setTextColor(parseColor("green"));
                break;
            case("Blue"):
                colHead.setTextColor(parseColor("blue"));
                break;
            default:
                break;

        }


    }

    //setLang method gets the TextView to be changed and sets the text using the getLang method
    private void setLang(String lang)
    {
        TextView tvLangHead = (TextView) findViewById(R.id.tvChosenOrNo);
        tvLangHead.setText(getLang(lang));
    }


    //geLang method changes the Header text also sets the default radio button
    private String getLang(String language)
    {
        String langHeader = "";

        //Change text depending on passed in language. Sets radio button to be that of the passed in language
        switch (language)
        {
            case("English"):
                langHeader = "Hello";
                RadioButton checkedButton = (RadioButton) findViewById(R.id.rbEnglish);
                checkedButton.setChecked(true);
                break;
            case("French"):
                langHeader = "Bonjour Le Monde";
                RadioButton checkedButton2 = (RadioButton) findViewById(R.id.rbFrench);
                checkedButton2.setChecked(true);
                break;
            case("German"):
                langHeader = "Hallo Welt";
                RadioButton checkedButton3 = (RadioButton) findViewById(R.id.rbGerman);
                checkedButton3.setChecked(true);
                break;
            case("Spanish"):
                langHeader = "Hola Mundo";
                RadioButton checkedButton4 = (RadioButton) findViewById(R.id.rbSpanish);
                checkedButton4.setChecked(true);
                break;
            default:
                break;

        }
        //return the new header text
        return langHeader;
    }

    //getSpinnerPos method returns the positon of the spinner that the passed in text is at.
    public Integer getSpinnerPos(String colourPreference)
    {
        Spinner spinner = (Spinner) findViewById(R.id.spnColour);
        ArrayAdapter myAdap = (ArrayAdapter) spinner.getAdapter();
        int spinnerPos = myAdap.getPosition(colourPreference);
        return spinnerPos;
    }

}
