package bit.jacksct1.multipleactivitiespract31task1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get button reference and set text
        Button btnNextAct = (Button)findViewById(R.id.btnNextAct);
        btnNextAct.setText(R.string.btnLabelForNextActivity);

        //Get TextView reference and set text
        TextView tvScreenNumber = (TextView) findViewById((R.id.tvScreenNumber));
        tvScreenNumber.setText(R.string.titleForScreenA);

        //Create handler and set onClickListener to button
        ChangeActivityViaButton handler = new ChangeActivityViaButton();
        btnNextAct.setOnClickListener(handler);
    }

    //Create inner class implementing OnClickListener
    class ChangeActivityViaButton implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            //Create Intent and call its startActivity method to go to next Activity
            Intent changeActivityIntent = new Intent(MainActivity.this, Main2Activity.class);
            startActivity(changeActivityIntent);
        }
    }
}
