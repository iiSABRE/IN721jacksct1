package bit.jacksct1.formattedfileio;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class FillListScreen extends AppCompatActivity {

    InputStream inputStream;
    JSONObject dunedinData;
    ArrayList<String> dunEvents = new ArrayList<String>();
    ArrayList<String> dunEventsDes = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_list_screen);

        getDataList();
        populateListView(dunEvents);

        ListView lvEvents = (ListView) findViewById(R.id.lvEvents);
        lvEvents.setOnItemClickListener(new listenerEvent());


    }

    public void getDataList()
    {

        String assetFileName = "dunedin_events.json";


        try {
            AssetManager am = getAssets();
            inputStream = am.open(assetFileName);

            int fileSizeInBytes = inputStream.available();

            byte[] JSONBuffer = new byte[fileSizeInBytes];

            inputStream.read(JSONBuffer);
            inputStream.close();
            String JSONInput = new String(JSONBuffer);
            dunedinData = new JSONObject(JSONInput);

            JSONObject eventsObject = dunedinData.getJSONObject("events");
            JSONArray eventsArray = eventsObject.getJSONArray("event");

            JSONObject eventOject = null;
            int nEvents = eventsArray.length();


            for (int i = 0; i<nEvents; i++)
            {
                JSONObject event = eventsArray.getJSONObject(i);
                String title = event.getString("title");
                String description = event.getString("description");
                dunEvents.add(title);
                dunEventsDes.add(description);


            }



        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }






    }

    public void populateListView(ArrayList eventArr)
    {
        ListView eventListView = (ListView) findViewById(R.id.lvEvents);
        eventListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, eventArr));
    }

    public void makeToast(String des)
    {
        Toast.makeText(this, des ,Toast.LENGTH_LONG).show();
    }


    public class listenerEvent implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            int pos = position;
            String desc = dunEventsDes.get(pos);
            makeToast(desc);
        }
    }
}
