package bit.jacksct1.lastfm2016;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

public class SearchSimilarScreen extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_similar_screen);

        Button btnSearch = (Button)findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new searchHandler());


    }

    class WebService extends AsyncTask<String,Void,String>
    {
        ArrayList<String> similarArray = new ArrayList<>();

        @Override
        protected String doInBackground(String... searchedName)
        {
            String JSONString = null;
            String test = searchedName[0];

            try
            {
                String urlString = "http://ws.audioscrobbler.com/2.0/?method=artist.getSimilar&artist="+test+"&limit=10&api_key=58384a2141a4b9737eacb9d0989b8a8c&format=json";

                URL URLObject = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) URLObject.openConnection();
                connection.connect();

                int responseCode = connection.getResponseCode();
                if (responseCode != 200)
                {
                    Toast.makeText(SearchSimilarScreen.this, "Failed to retrieve data from web", Toast.LENGTH_LONG).show();
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
                Toast.makeText(SearchSimilarScreen.this, "Failed to get JSONString", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                Toast.makeText(SearchSimilarScreen.this, "Failed to get JSONString", Toast.LENGTH_LONG).show();
            }

            return JSONString;
        }

        @Override
        protected void onPostExecute(String fetchedString)
        {

            try
            {
                JSONObject simArtists = new JSONObject(fetchedString);
                JSONObject artistsObject = simArtists.getJSONObject("similarartists");
                JSONArray artistArray = artistsObject.getJSONArray("artist");

                int nArtists = artistArray.length();

                //Loops through entire array of event objects and getting their title and description
                //Putting those Strings into seperate arrays
                for (int i = 0; i<nArtists; i++)
                {
                    JSONObject similar = artistArray.getJSONObject(i);
                    String name = similar.getString("name");

                    similarArray.add(name);


                }
                fillList(similarArray);

            }
            catch (JSONException e)
            {
                Toast.makeText(SearchSimilarScreen.this,"Couldnt get artist information", Toast.LENGTH_LONG).show();
            }


        }


    }

    private class searchHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
           // similarArray.clear();

            EditText etName = (EditText)findViewById(R.id.etName);
            String searchedName = etName.getText().toString();

            TextView tx = (TextView) findViewById(R.id.tvSearchHeader);
            tx.setText(searchedName);

            WebService APIThread = new WebService();
            APIThread.execute(searchedName);



        }
    }

    protected void fillList(ArrayList similarArray)
    {
        ArrayAdapter similarAdaptor = new ArrayAdapter(SearchSimilarScreen.this, android.R.layout.simple_list_item_1, similarArray);
        ListView lvSim = (ListView) findViewById(R.id.lvSimilar);
        lvSim.setAdapter(similarAdaptor);

    }

}
