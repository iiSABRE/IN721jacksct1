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
        String latitude = Integer.toString(generator.nextInt(180) - 90);
        return latitude;
    }

    public String getLong()
    {
        String longitude = Integer.toString(generator.nextInt(360) - 180);
        return longitude;
    }






}
