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

        Button btnNextAct = (Button)findViewById(R.id.btnNextAct);
        btnNextAct.setText(R.string.btnLabelForNextActivity);

        TextView tvScreenNumber = (TextView) findViewById((R.id.tvScreenNumber));
        tvScreenNumber.setText(R.string.titleForScreenA);

        ChangeActivityViaButton handler = new ChangeActivityViaButton();
        btnNextAct.setOnClickListener(handler);
    }

    class ChangeActivityViaButton implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            Intent changeActivityIntent = new Intent(MainActivity.this, Main2Activity.class);
            startActivity(changeActivityIntent);
        }
    }
}
