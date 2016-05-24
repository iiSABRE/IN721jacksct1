package bit.jacksct1.teleportmachine;

import java.util.Random;

/**
 * Created by jacksct1 on 10/05/2016.
 */
public class Manager
{

    Random generator = new Random();

    public Double getLat()
    {
        double lat = -90.000 + generator.nextDouble() * 180;

        return lat;
    }

    public Double getLong()
    {
        double lng = -180.000 + generator.nextDouble() * 360;

        return lng;
    }






}
