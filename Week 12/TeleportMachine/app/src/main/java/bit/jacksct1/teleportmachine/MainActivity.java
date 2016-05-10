package bit.jacksct1.teleportmachine;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String latitude;
    String longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button teleport = (Button) findViewById(R.id.btnTeleport);
        teleport.setOnClickListener(new teleportClick());



    }

    private class teleportClick implements View.OnClickListener {
        @Override
        public void onClick(View v)
        {

            WebService APIThread = new WebService();
            APIThread.execute();
        }
    }

    class WebService extends AsyncTask<Void,Void,String>
    {

        @Override
        protected String doInBackground(Void... params)
        {
            String JSONString = null;
            Manager manager = new Manager();

            latitude = manager.getLat();
            longitude = manager.getLong();

            boolean nonExists = true;
            while (nonExists)
            {
                try {
                    String urlString = "http://www.geoplugin.net/extras/location.gp?lat=" + latitude + "&long=" + longitude + "&format=json";

                    URL URLObject = new URL(urlString);
                    HttpURLConnection connection = (HttpURLConnection) URLObject.openConnection();
                    connection.connect();

                    int responseCode = connection.getResponseCode();
                    if (responseCode != 200) {
                        Toast.makeText(MainActivity.this, "Failed to retrieve data from web", Toast.LENGTH_LONG).show();
                    } else {
                        InputStream inputStream = connection.getInputStream();
                        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                        String responseString;
                        StringBuilder stringBuilder = new StringBuilder();
                        while ((responseString = bufferedReader.readLine()) != null) {
                            stringBuilder = stringBuilder.append(responseString);
                        }
                        JSONString = stringBuilder.toString();

                        if (!(JSONString.equals(getResources().getString(R.string.noNearestCheck))))
                        {
                            nonExists = false;
                        }
                        else
                        {
                            latitude = manager.getLat();
                            longitude = manager.getLong();
                        }
                    }

                } catch (MalformedURLException e) {
                    Toast.makeText(MainActivity.this, "Failed to get JSONString", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    Toast.makeText(MainActivity.this, "Failed to get JSONString", Toast.LENGTH_LONG).show();
                }
            }
            return JSONString;
        }

        @Override
        protected void onPostExecute(String fetchedString)
        {


            try {
                if (fetchedString.equals(getResources().getString(R.string.noNearestCheck)))
                {
                    Toast.makeText(MainActivity.this, "No nearest city", Toast.LENGTH_LONG).show();
                }
                else
                {
                    JSONObject nearestCity = new JSONObject(fetchedString);


                    String closestcity = nearestCity.getString("geoplugin_place");
                    String country = nearestCity.getString("geoplugin_countryCode");
                    TextView nearest = (TextView) findViewById(R.id.tvNearest);
                    nearest.setText("Closest City: " + closestcity +", " + country);

                    TextView tvLong = (TextView) findViewById(R.id.tvLongValue);
                    tvLong.setText(longitude);

                    TextView tvLat = (TextView) findViewById(R.id.tvLatValue);
                    tvLat.setText(latitude);

                }



            }
            catch (JSONException e)
            {
                Toast.makeText(MainActivity.this,"Couldnt get location information", Toast.LENGTH_LONG).show();
            }

        }


    }
}
