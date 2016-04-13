package bit.jacksct1.lastfm2016;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MenuScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);

        ListView menuListView = (ListView)findViewById(R.id.lvMenu);

        String[] menuArray = new String[]{"Top 20 Artists", "Search Similar Artists", "View Number 1"};
        menuListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menuArray));


    }
}


