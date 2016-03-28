package bit.jacksct1.languagetrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class QuestionScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_screen);

        Intent intent = this.getIntent();
        UtilityManager manager = (UtilityManager)intent.getSerializableExtra("Manager");




        TextView questionNumber = (TextView) findViewById(R.id.tvQuestionNumber);
        questionNumber.setText(manager.getQuestionNumber());







    }
}
