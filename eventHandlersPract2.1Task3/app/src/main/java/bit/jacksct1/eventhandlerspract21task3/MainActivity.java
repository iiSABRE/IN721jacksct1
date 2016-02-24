package bit.jacksct1.eventhandlerspract21task3;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText EightCharName = (EditText)findViewById(R.id.etUsername);
        MinEight handler = new MinEight();
        EightCharName.setOnKeyListener(handler);


    }

    public class MinEight implements View.OnKeyListener
    {

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event)
        {
            if (keyCode == KeyEvent.KEYCODE_ENTER)
            {
                if(event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    EditText EightCharName = (EditText)findViewById(R.id.etUsername);
                    Resources resourceResolver = getResources();

                    if(EightCharName.length() >= resourceResolver.getInteger(R.integer.min_char_username))
                    {
                        Toast.makeText(MainActivity.this, "Thank you " + EightCharName.getText(), Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Usernames must be 8 characters, " + EightCharName.getText(), Toast.LENGTH_LONG).show();
                    }

                }
            }
            return false;
        }
    }
}
