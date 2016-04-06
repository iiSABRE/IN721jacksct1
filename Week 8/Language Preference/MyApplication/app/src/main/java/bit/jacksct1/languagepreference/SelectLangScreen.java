package bit.jacksct1.languagepreference;

import android.content.SharedPreferences;
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

import static android.graphics.Color.parseColor;

public class SelectLangScreen extends AppCompatActivity {

    SharedPreferences prefs;
    SharedPreferences.Editor prefsEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_lang_screen);

        Button btnSetPref = (Button)  findViewById(R.id.btnSetPreferences);
        btnSetPref.setOnClickListener(new preferencesBtnHandler());

        prefs = getSharedPreferences("prefsDemo", MODE_PRIVATE);
        prefsEditor = prefs.edit();

        String languagePreference = prefs.getString("language", null);
        String colourPreference = prefs.getString("colour", null);

        if(languagePreference != null)
        {
            setLang(languagePreference);
        }

        Spinner colourSpinner = (Spinner) findViewById(R.id.spnColour);
        int layoutID = android.R.layout.simple_spinner_item;
        Resources res = getResources();
        String[] coloursArr = res.getStringArray(R.array.selectColourArray);
        ArrayAdapter<String> colourAdapter = new ArrayAdapter<String>(this, layoutID, coloursArr);
        colourSpinner.setAdapter(colourAdapter);


        if(colourPreference != null)
        {
            setColour(colourPreference);
        }


    }

    private class preferencesBtnHandler implements View.OnClickListener {
        @Override
        public void onClick(View v)
        {
            RadioGroup rg = (RadioGroup) findViewById(R.id.rgLanguage);
            int checkedID = rg.getCheckedRadioButtonId();
            RadioButton checkedButton = (RadioButton)findViewById(checkedID);
            String checkLang = checkedButton.getText().toString();

            prefsEditor.putString("language", checkLang);

            Spinner spinner = (Spinner) findViewById(R.id.spnColour);
            String colour = spinner.getSelectedItem().toString();

            prefsEditor.putString("colour", colour);
            prefsEditor.commit();
            setLang(checkLang);
            setColour(colour);



        }
    }

    private void setColour(String colour)
    {

        TextView colHead = (TextView) findViewById(R.id.tvChosenOrNo);

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

    private void setLang(String lang)
    {
        TextView tvLangHead = (TextView) findViewById(R.id.tvChosenOrNo);
        tvLangHead.setText(getLang(lang));
    }



    private String getLang(String language)
    {
        String langHeader = "";

        switch (language)
        {
            case("English"):
                langHeader = "Hello";
                break;
            case("French"):
                langHeader = "Bonjour Le Monde";
                break;
            case("German"):
                langHeader = "Hallo Welt";
                break;
            case("Spanish"):
                langHeader = "Hola Mundo";
                break;
            default:
                break;

        }
        return langHeader;
    }

}
