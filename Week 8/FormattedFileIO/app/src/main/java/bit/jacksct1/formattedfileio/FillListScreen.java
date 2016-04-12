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
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_list_screen);

        //Calling methods to get data annd populate the listview
        getDataList();
        populateListView(dunEvents);

        ListView lvEvents = (ListView) findViewById(R.id.lvEvents);
        lvEvents.setOnItemClickListener(new listenerEvent());


    }

    //The getDataList method pulls the data from the JSON file and puts in an arrays for use
    public void getDataList()
    {

        String assetFileName = "dunedin_events.json";

        try {

            //Reading the JSOn file and getting to the array that holds events
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

            //Loops through entire array of event objects and getting their title and description
            //Putting those Strings into seperate arrays
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

    //populateListView accepts an array to be passed nito the listview
    public void populateListView(ArrayList eventArr)
    {
        ListView eventListView = (ListView) findViewById(R.id.lvEvents);
        eventListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, eventArr));
    }

    //makeToast method creates a toast with the String that is passed when called
    public void makeToast(String des)
    {
        Toast.makeText(this, des ,Toast.LENGTH_LONG).show();
    }


    //listenerEvent calls handles clicks on the listview
    public class listenerEvent implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            //Gets the position the clicked item was in the listview and calls the toast method with the corresponding description from the des array
            String desc = dunEventsDes.get(position);
            makeToast(desc);
        }
    }
}
