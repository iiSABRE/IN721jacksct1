package bit.jacksct1.multipleactivitiespract31task1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnNextAct = (Button)findViewById(R.id.btnNextAct);
        btnNextAct.setText(R.string.btnLabelToGoToWebsite);

        TextView tvScreenNumber = (TextView) findViewById((R.id.tvScreenNumber));
        tvScreenNumber.setText(R.string.titleForScreenC);

        ChangeActivityViaButton handler = new ChangeActivityViaButton();
        btnNextAct.setOnClickListener(handler);
    }

    class ChangeActivityViaButton implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            Uri goSeeReddit = Uri.parse(getString(R.string.websiteUrlReddit));
            Intent changeActivityIntent = new Intent(Intent.ACTION_VIEW, goSeeReddit);
            startActivity(changeActivityIntent);
        }
    }


}
