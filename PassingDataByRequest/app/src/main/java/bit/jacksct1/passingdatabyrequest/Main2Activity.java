package bit.jacksct1.passingdatabyrequest;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView colouredText = (TextView) findViewById(R.id.tvColouredText);
        Integer colour = colouredText.getCurrentTextColor();


        Intent returnIntent = new Intent();
        returnIntent.putExtra("colour", colour);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
