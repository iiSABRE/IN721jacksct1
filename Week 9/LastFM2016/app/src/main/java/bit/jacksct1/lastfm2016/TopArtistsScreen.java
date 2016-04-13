package bit.jacksct1.lastfm2016;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TopArtistsScreen extends AppCompatActivity {

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

        protected void onPostExecute(String fetchedString)
        {
            Toast.makeText(TopArtistsScreen.this,fetchedString, Toast.LENGTH_LONG).show();
        }
    }
}
