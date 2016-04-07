package bit.jacksct1.simplefileio;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainScreen extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        ArrayList<String> houseNamesArray = new ArrayList<String>();

        String assetFileName = "great_houses.txt";

        AssetManager am = getAssets();

        InputStream is = null;
        try {
            is = am.open(assetFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStreamReader ir = new InputStreamReader(is);

        BufferedReader br = new BufferedReader(ir);

        String newHouse;
        try {
            while ((newHouse = br.readLine()) != null)
            {
                houseNamesArray.add(newHouse);
            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }


        ListView houseList = (ListView) findViewById(R.id.lvHouses);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, houseNamesArray);
        houseList.setAdapter(arrayAdapter);

    }
}
