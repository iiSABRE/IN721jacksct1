package bit.jacksct1.lastfm2016;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

        menuListView.setOnItemClickListener(new listenerEvent());



    }

    //listenerEvent calls handles clicks on the listview
    public class listenerEvent implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //Gets the position the clicked item was in the listview and create intent to click activity
            switch (position) {
                case 0:
                    Intent intent = new Intent(MenuScreen.this, TopArtistsScreen.class);
                    startActivity(intent);
                    break;
                case 1:
                    Intent intent2 = new Intent(MenuScreen.this, SearchSimilarScreen.class);
                    startActivity(intent2);
                    break;
                case 2:
                    Intent intent3 = new Intent(MenuScreen.this, SeeTopArtistScreen.class);
                    startActivity(intent3);
                    break;
                default:
                    break;

            }
        }
    }
}


