package bit.jacksct1.eventhandlerspract21task2;

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

        EditText NoAtMesaageBox = (EditText)findViewById(R.id.etNoAt);
        NoAtMessage handler = new NoAtMessage();
        NoAtMesaageBox.setOnKeyListener(handler);

    }

    public class NoAtMessage implements View.OnKeyListener
    {

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event)
        {
            if (keyCode == KeyEvent.KEYCODE_AT)
            {
                if(event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    Toast.makeText(MainActivity.this, "Don't type @", Toast.LENGTH_LONG).show();
                }
            }
            return false;
        }
    }
}