package bit.jacksct1.lastfm2016;

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

public class SeeTopArtistScreen extends AppCompatActivity {

    String imgURLString;
    Bitmap personImg;
    InputStream inputStream = null;
    String urlImgString;
    String JSONString;
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_top_artist_screen);

        Button btnView = (Button) findViewById(R.id.btnView);
        btnView.setOnClickListener(new viewHandler());

    }

    class WebService extends AsyncTask<Void,Void,Bitmap>
    {

        protected InputStream getInput(String url)
        {

            URL URLObject = null;
            String JSONString = null;
            try
            {
                URLObject = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) URLObject.openConnection();
                connection.connect();
                int responseCode = connection.getResponseCode();
                if (responseCode != 200)
                {
                    Toast.makeText(SeeTopArtistScreen.this, "Failed to retrieve data from web", Toast.LENGTH_LONG).show();
                }
                else
                {
                    inputStream = connection.getInputStream();

                }
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return inputStream;
        }


        @Override
        protected Bitmap doInBackground(Void... params)
        {

            String urlString = "http://ws.audioscrobbler.com/2.0/?method=chart.getTopArtists&limit=1&api_key=58384a2141a4b9737eacb9d0989b8a8c&format=json";


            try
            {

                InputStreamReader inputStreamReader = new InputStreamReader(getInput(urlString));
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String responseString;
                StringBuilder stringBuilder = new StringBuilder();
                while((responseString = bufferedReader.readLine()) != null)
                {
                    stringBuilder = stringBuilder.append(responseString);
                }
                JSONString = stringBuilder.toString();

                JSONObject topArtists = new JSONObject(JSONString);

                JSONObject artistsObject = topArtists.getJSONObject("artists");
                JSONArray artistArray = artistsObject.getJSONArray("artist");
                JSONObject first = artistArray.getJSONObject(0);
                JSONArray image = first.getJSONArray("image");
                JSONObject largeImg = image.getJSONObject(3);
                name = largeImg.getString("#text");



                personImg = BitmapFactory.decodeStream(getInput(name));



            }
            catch (JSONException e)
            {
                Toast.makeText(SeeTopArtistScreen.this,"Couldnt get artist information", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return personImg;
        }

        @Override
        protected void onPostExecute(Bitmap fetchedString)
        {

            ImageView img = (ImageView) findViewById(R.id.ivTopPer);
            img.setImageBitmap(fetchedString);

        }


    }




    private class viewHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {
            WebService webSer = new WebService();
            webSer.execute();

        }
    }
}
