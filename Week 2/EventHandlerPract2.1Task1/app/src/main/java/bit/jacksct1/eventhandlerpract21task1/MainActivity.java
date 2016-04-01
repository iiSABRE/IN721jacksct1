package bit.jacksct1.eventhandlerpract21task1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button BtnMessageBox = (Button)findViewById(R.id.btnEventTester);
        ShortButtonMessageBox handler = new ShortButtonMessageBox();
        BtnMessageBox.setOnClickListener(handler);

        LongButtonMessageBox handler2 = new LongButtonMessageBox();
        BtnMessageBox.setOnLongClickListener(handler2);

    }

    public class LongButtonMessageBox implements View.OnLongClickListener
    {
        @Override
        public boolean onLongClick(View v)
        {
            Toast.makeText(MainActivity.this, "Longer Click", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public class ShortButtonMessageBox implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {
            Toast.makeText(MainActivity.this, "Short Click", Toast.LENGTH_LONG).show();
        }
    }

}
