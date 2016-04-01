package bit.jacksct1.passingdatabetweenactivities;

import android.content.DialogInterface;
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

        Button toSettings = (Button) findViewById(R.id.btnSettings);
        toSettings.setOnClickListener(new GoToSettings());

        Intent launchIntent = getIntent();
        String userName = launchIntent.getStringExtra("username");

        if(userName != null)
        {
            TextView username = (TextView) findViewById(R.id.tvSubHeading);
            username.setText(userName);
        }

    }

    public class GoToSettings implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {
            Intent changeActivityIntent = new Intent(MainActivity.this, Main2Activity.class);
            startActivity(changeActivityIntent);
        }
    }
}
