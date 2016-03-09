package bit.jacksct1.welcometodunedinlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SetUpListView();

        ListView toDoListView = (ListView) findViewById(R.id.lvThingsToDo);
        toDoListView.setOnItemClickListener(new DunedinActivityListHandler());

    }

    public class DunedinActivityListHandler implements AdapterView.OnItemClickListener
    {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            String clickedItem = (String) parent.getItemAtPosition(position).toString();
            Intent newIntent;

            switch(clickedItem)
            {
                case "Activities":
                    newIntent = new Intent(MainActivity.this, ActivityActivity.class);
                    break;
                case "Shopping":
                    newIntent = new Intent(MainActivity.this, ShoppingActivity.class);
                    break;
                case "Dining":
                    newIntent = new Intent(MainActivity.this, DiningActivity.class);
                    break;
                case "Services":
                    newIntent = new Intent(MainActivity.this, ServicesActivity.class);
                    break;
                default:
                    newIntent = null;
                    break;
            }

            if(newIntent != null)
            {
                startActivity(newIntent);
            }


        }
    }

    public void SetUpListView()
    {
        String[] toDo = {"Activities", "Shopping", "Dining", "Servives"};
        ArrayAdapter<String> toDoAdapter = new ArrayAdapter<String>(this, R.layout.activity_main, toDo);
        ListView toDoListView = (ListView) findViewById(R.id.lvThingsToDo);
        toDoListView.setAdapter(toDoAdapter);
    }
}
