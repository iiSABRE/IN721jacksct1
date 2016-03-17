package bit.jacksct1.passingdatabetweenactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button toMain = (Button) findViewById(R.id.btnReturn);
        toMain.setOnClickListener(new GoToMain());

    }

    public class GoToMain implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {
            EditText editUsername = (EditText) findViewById(R.id.etUsername);

            if (editUsername.getText().length() >= 8)
            {
                String username = editUsername.getText().toString();
                Intent backToMainIntent = new Intent(Main2Activity.this, MainActivity.class);
                backToMainIntent.putExtra("username", username);
                startActivity(backToMainIntent);
            }

            else
            {
                Toast.makeText(Main2Activity.this, "Username must be atleast 8 characters", Toast.LENGTH_LONG).show();
            }

        }
    }


}
