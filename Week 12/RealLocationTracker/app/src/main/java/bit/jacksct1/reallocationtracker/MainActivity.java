package bit.jacksct1.reallocationtracker;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
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

public class MainActivity extends AppCompatActivity {


    double lat;
    double lng;
    String closestcity;
    String farmURL;
    Bitmap closePhoto;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria defaultCriteria = new Criteria();

        String providerName = locationManager.getBestProvider(defaultCriteria, false);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location currentLocation = locationManager.getLastKnownLocation(providerName);

        lat = currentLocation.getLatitude();
        lng = currentLocation.getLongitude();

        TextView tvLat = (TextView) findViewById(R.id.tvLatValue);
        TextView tvLong = (TextView) findViewById(R.id.tvLongValue);

        tvLat.setText(Double.toString(lat));
        tvLong.setText(Double.toString(lng));


        locationManager.requestLocationUpdates(providerName, 10000, 1, new CustomLoactionListener());

        WebService APIThread = new WebService();
        APIThread.execute();

    }

    public class CustomLoactionListener implements LocationListener
    {
        @Override
        public void onLocationChanged(Location location)
        {
            lat = location.getLatitude();
            lng = location.getLongitude();

            TextView tvLat = (TextView) findViewById(R.id.tvLatValue);
            TextView tvLong = (TextView) findViewById(R.id.tvLongValue);

            tvLat.setText(Double.toString(lat));
            tvLong.setText(Double.toString(lng));

            WebService APIThread = new WebService();
            APIThread.execute();
        }


        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }





    class WebService extends AsyncTask<Void, Integer, String> {

        @Override
        protected String doInBackground(Void... params)
        {
            String JSONString = null;


                try
                {

                    // publishProgress(0);
                    String urlString = "http://www.geoplugin.net/extras/location.gp?lat=" + lat + "&long=" + lng + "&format=json";

                    URL URLObject = new URL(urlString);
                    HttpURLConnection connection = (HttpURLConnection) URLObject.openConnection();
                    connection.connect();

                    int responseCode = connection.getResponseCode();
                    if (responseCode != 200)
                    {
                        Toast.makeText(MainActivity.this, "Failed to retrieve data from web", Toast.LENGTH_LONG).show();
                    }
                    else {
                        InputStream inputStream = connection.getInputStream();
                        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                        String responseString;
                        StringBuilder stringBuilder = new StringBuilder();
                        while ((responseString = bufferedReader.readLine()) != null) {
                            stringBuilder = stringBuilder.append(responseString);
                        }
                        JSONString = stringBuilder.toString();

                    }


                } catch (MalformedURLException e) {
                    Toast.makeText(MainActivity.this, "Failed to get JSONString", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    Toast.makeText(MainActivity.this, "Failed to get JSONString", Toast.LENGTH_LONG).show();
                }

            return JSONString;
        }

        @Override
        protected void onPostExecute(String fetchedString) {


            try {
                if (fetchedString.equals(getResources().getString(R.string.noNearestCheck))) {
                    Toast.makeText(MainActivity.this, "No nearest city near you", Toast.LENGTH_LONG).show();
                } else {
                    JSONObject nearestCity = new JSONObject(fetchedString);


                    closestcity = nearestCity.getString("geoplugin_place");
                    String country = nearestCity.getString("geoplugin_countryCode");
                    TextView nearest = (TextView) findViewById(R.id.tvNearest);
                    nearest.setText("Closest City: " + closestcity + ", " + country);

                    FlickrService APIThread2 = new FlickrService();
                    APIThread2.execute();
                }


            } catch (JSONException e) {
                Toast.makeText(MainActivity.this, "Couldnt get location information 1", Toast.LENGTH_LONG).show();
            }




        }


    }

    class FlickrService extends AsyncTask<Void, Void, String> {


        @Override
        protected String doInBackground(Void... params) {
            String JSONStrings = null;

            try {
                String urlStringFlickr = "https://api.flickr.com/services/rest/?format=json&nojsoncallback=1&method=flickr.photos.search&api_key=eda41a123d459be0f85276d37290651e&tags=" + closestcity;

                URL URLObject = new URL(urlStringFlickr);
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
                    JSONStrings = stringBuilder.toString();

                }

            } catch (MalformedURLException e) {
                Toast.makeText(MainActivity.this, "Failed to get JSONString", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                Toast.makeText(MainActivity.this, "Failed to get JSONString", Toast.LENGTH_LONG).show();
            }

            return JSONStrings;
        }


        @Override
        protected void onPostExecute(String fetchedString) {



            try

            {

                JSONObject photos = new JSONObject(fetchedString);

                JSONObject photosObject = photos.getJSONObject("photos");
                JSONArray photoArray = photosObject.getJSONArray("photo");
                JSONObject firstPhoto = photoArray.getJSONObject(0);

                if((photoArray.toString().equals("[]"))||(photoArray==null))
                {
                    Toast.makeText(MainActivity.this, "No Images Found", Toast.LENGTH_LONG).show();
                }
                else
                {

                    String farm = firstPhoto.getString("farm");
                    String server = firstPhoto.getString("server");
                    String image = firstPhoto.getString("id");
                    String secret = firstPhoto.getString("server");

                    farmURL = "https://farm" + farm + ".staticflickr.com/" + server + "/" + image + "_" + secret + ".jpg";


                    ImageService APIThread3 = new ImageService();
                    APIThread3.execute();
                }



            } catch (JSONException e) {
                Toast.makeText(MainActivity.this, "No Images Found", Toast.LENGTH_LONG).show();
            }



        }

    }





    class ImageService extends AsyncTask<Void,Void,Bitmap>
    {


        @Override
        protected Bitmap doInBackground(Void... params)
        {

            try
            {
                URL URLObject = new URL(farmURL);
                HttpURLConnection connection = (HttpURLConnection) URLObject.openConnection();
                connection.connect();
                int responseCode = connection.getResponseCode();
                if (responseCode != 200)
                {
                    Toast.makeText(MainActivity.this, "Failed to retrieve image from web", Toast.LENGTH_LONG).show();
                }
                else
                {
                    closePhoto = null;
                    InputStream inputStream = connection.getInputStream();
                    closePhoto = BitmapFactory.decodeStream(inputStream);


                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return closePhoto;
        }

        @Override
        protected void onPostExecute(Bitmap fetchedString)
        {

            ImageView img = (ImageView) findViewById(R.id.ivFarm);
            img.setImageBitmap(fetchedString);


        }


    }
}
