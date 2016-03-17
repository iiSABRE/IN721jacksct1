package bit.jacksct1.passingdatabyrequest;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button goToSettings = (Button) findViewById(R.id.btnChangeColour);
        goToSettings.setOnClickListener(new GoToSettings());


    }

    public class GoToSettings implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {


                Intent requestColourIntent = new Intent(MainActivity.this, Main2Activity.class);
                startActivityForResult(requestColourIntent,0);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if ((requestCode == 0 && (resultCode == Activity.RESULT_OK)))
        {
            String result = data.getStringExtra("colour");

            TextView mainText = (TextView) findViewById(R.id.tvBigText);
            mainText.setText(result);

        }
    }
}
