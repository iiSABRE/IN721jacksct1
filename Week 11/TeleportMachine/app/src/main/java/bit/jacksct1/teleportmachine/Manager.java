package bit.jacksct1.teleportmachine;

import java.util.Random;

/**
 * Created by jacksct1 on 10/05/2016.
 */
public class Manager
{

    Random generator = new Random();

    public String getLat()
    {
        double lat = -90.000 + generator.nextDouble() * 180;
        String latitude = Double.toString(lat);
        return latitude;
    }

    public String getLong()
    {
        double lng = -180.000 + generator.nextDouble() * 360;
        String longitude = Double.toString(lng);
        return longitude;
    }






}
