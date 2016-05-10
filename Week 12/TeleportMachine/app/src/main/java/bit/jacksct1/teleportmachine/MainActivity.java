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
    ProgressDialog progressDialog;
    String closestcity;
    String farmURL;
    Bitmap closePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button teleport = (Button) findViewById(R.id.btnTeleport);
        teleport.setOnClickListener(new teleportClick());


    }

    private class teleportClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            ImageView image = (ImageView) findViewById(R.id.ivFarm);
            image.setImageResource(0);
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

            try {
                if (fetchedString.equals(getResources().getString(R.string.noNearestCheck))) {
                    Toast.makeText(MainActivity.this, "No nearest city", Toast.LENGTH_LONG).show();
                } else {
                    JSONObject nearestCity = new JSONObject(fetchedString);


                    closestcity = nearestCity.getString("geoplugin_place");
                    String country = nearestCity.getString("geoplugin_countryCode");
                    TextView nearest = (TextView) findViewById(R.id.tvNearest);
                    nearest.setText("Closest City: " + closestcity + ", " + country);

                    TextView tvLong = (TextView) findViewById(R.id.tvLongValue);
                    tvLong.setText(longitude);

                    TextView tvLat = (TextView) findViewById(R.id.tvLatValue);
                    tvLat.setText(latitude);


                }


            } catch (JSONException e) {
                Toast.makeText(MainActivity.this, "Couldnt get location information 1", Toast.LENGTH_LONG).show();
            }

            FlickrService APIThread2 = new FlickrService();
            APIThread2.execute();


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

