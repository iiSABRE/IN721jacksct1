package bit.jacksct1.teleportmachine;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

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

    Double latitude = 0.00;
    Double longitude = 0.00;
    LatLng startLatLng;
    GoogleMap mMap;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button teleport = (Button) findViewById(R.id.btnTeleport);
        teleport.setOnClickListener(new teleportClick());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new MApCallBackClass());

    }

    public class MApCallBackClass implements OnMapReadyCallback
    {

        @Override
        public void onMapReady(GoogleMap googleMap)
        {
            startLatLng = new LatLng(latitude, longitude);
            mMap = googleMap;
            mMap.addMarker(new MarkerOptions().position(startLatLng).title("Dead Centre"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(startLatLng));
        }
    }

    private class teleportClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {


            WebService APIThread = new WebService();
            APIThread.execute();


        }
    }

    class WebService extends AsyncTask<Void, Integer, String> {
        @Override
        protected void onPreExecute() {


            progressDialog = ProgressDialog.show(MainActivity.this, "", "Loading.....Attempts 0", true);

        }


        @Override
        protected String doInBackground(Void... params) {
            String JSONString = null;
            Manager manager = new Manager();

            latitude = manager.getLat();
            longitude = manager.getLong();

            boolean nonExists = true;
            Integer progress = 0;
            while (nonExists) {


                try {

                    // publishProgress(0);
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

                        if (!(JSONString.equals(getResources().getString(R.string.noNearestCheck)))) {
                            nonExists = false;
                        } else {
                            latitude = manager.getLat();
                            longitude = manager.getLong();
                            progress++;
                            publishProgress(progress);
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
        protected void onProgressUpdate(Integer... progress) {


            super.onProgressUpdate(progress);

            progressDialog.setMessage("Loading.....Attempts " + progress[0]);


        }

        @Override
        protected void onPostExecute(String fetchedString) {
            progressDialog.dismiss();
            mMap.clear();
            startLatLng = new LatLng(latitude, longitude);
            mMap.addMarker(new MarkerOptions().position(startLatLng).title("Dead Centre"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(startLatLng));

        }


    }



}

