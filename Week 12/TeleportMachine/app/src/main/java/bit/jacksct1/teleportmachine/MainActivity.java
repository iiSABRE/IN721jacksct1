package bit.jacksct1.teleportmachine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random generator = new Random();
    int max = 90;
    int min = -90;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button teleport = (Button) findViewById(R.id.btnTeleport);
        teleport.setOnClickListener(new teleportClick());

    }

    private class teleportClick implements View.OnClickListener {
        @Override
        public void onClick(View v)
        {

            TextView tvLong = (TextView) findViewById(R.id.tvLongValue);
            tvLong.setText(new Manager().getLong());

            TextView tvLat = (TextView) findViewById(R.id.tvLatValue);
            tvLat.setText(new Manager().getLat());
        }
    }
}
