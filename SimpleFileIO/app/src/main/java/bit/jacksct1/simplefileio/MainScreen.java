package bit.jacksct1.simplefileio;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainScreen extends AppCompatActivity {

    ArrayList<String> houseNamesArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        houseNamesArray = new ArrayList<String>();

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

        String newHouse = null;
        try {
            while ((newHouse = br.readLine()) != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        {
            houseNamesArray.add(newHouse);
        }
    }
}
