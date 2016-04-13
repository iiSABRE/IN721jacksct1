package bit.jacksct1.lastfm2016;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class TopArtistsScreen extends AppCompatActivity {

    JSONObject topArtists;
    ArrayList<String> topArtist = new ArrayList<String>();
    ArrayList<String> artistListeners = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_artists_screen);

        WebService APIThread = new WebService();
        APIThread.execute();

    }

    class WebService extends AsyncTask<Void,Void,String>
    {

        @Override
        protected String doInBackground(Void... params)
        {
            String JSONString = null;
            try
            {
                String urlString = "http://ws.audioscrobbler.com/2.0/?method=chart.getTopArtists&limit=20&api_key=58384a2141a4b9737eacb9d0989b8a8c&format=json";

                URL URLObject = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) URLObject.openConnection();
                connection.connect();

                int responseCode = connection.getResponseCode();
                if (responseCode != 200)
                {
                    Toast.makeText(TopArtistsScreen.this, "Failed to retrieve data from web", Toast.LENGTH_LONG).show();
                }
                else
                {
                    InputStream inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    String responseString;
                    StringBuilder stringBuilder = new StringBuilder();
                    while((responseString = bufferedReader.readLine()) != null)
                    {
                        stringBuilder = stringBuilder.append(responseString);
                    }
                    JSONString = stringBuilder.toString();
                }

            } catch (MalformedURLException e) {
                Toast.makeText(TopArtistsScreen.this, "Failed to get JSONString", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                Toast.makeText(TopArtistsScreen.this, "Failed to get JSONString", Toast.LENGTH_LONG).show();
            }

            return JSONString;
        }

        @Override
        protected void onPostExecute(String fetchedString)
        {

            try
            {
                topArtists = new JSONObject(fetchedString);
                JSONObject artistsObject = topArtists.getJSONObject("artists");
                JSONArray artistArray = artistsObject.getJSONArray("artist");

                int nArtists = artistArray.length();

                //Loops through entire array of event objects and getting their title and description
                //Putting those Strings into seperate arrays
                for (int i = 0; i<nArtists; i++)
                {
                    JSONObject event = artistArray.getJSONObject(i);
                    String name = event.getString("name");
                    String count = event.getString("listeners");
                    topArtist.add(name + " " + count);
                    //artistListeners.add(count);
                    populateListview(topArtist);

                }
            }
            catch (JSONException e)
            {
                Toast.makeText(TopArtistsScreen.this,"", Toast.LENGTH_LONG).show();
            }

        }
    }

    protected void populateListview (ArrayList artists)
    {
        ListView topListView = (ListView) findViewById(R.id.lvTop20);
        topListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, artists));
    }
}
