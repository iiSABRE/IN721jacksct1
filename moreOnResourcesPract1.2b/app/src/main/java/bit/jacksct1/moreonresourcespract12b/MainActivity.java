package bit.jacksct1.moreonresourcespract12b;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources resourceResolver = getResources();
        TextView txtDisplay = (TextView) findViewById(R.id.txtMain);
        printArray(resourceResolver,txtDisplay);

    }

    public void printArray(Resources resourceResolver, TextView txtDisplay)
    {
        String mainText =resourceResolver.getString(R.string.fridays);
        txtDisplay.setText(mainText);

        int febArray[] = resourceResolver.getIntArray(R.array.FebFridays);
        for(int i=0; i<febArray.length; i++)
        {
            txtDisplay.append(" " + febArray[i]);
        }
    }


}
