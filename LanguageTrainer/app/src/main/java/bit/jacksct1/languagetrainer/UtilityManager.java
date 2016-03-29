package bit.jacksct1.languagetrainer;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by jacksct1 on 28/03/2016.
 */
public class UtilityManager
{



    public void sortArray(ArrayList array)
    {
        for (int i = 0;i<200;i++)
        {
            Random rand = new Random();
            int firstToSwap = rand.nextInt(array.size());
            int secondToSwap =0;
            boolean same = false;
            while(!same)
            {
                secondToSwap = rand.nextInt(array.size());
                if (firstToSwap != secondToSwap)
                {
                    same = true;
                }
            }

            Object temp = array.set( firstToSwap, array.get( secondToSwap ) ) ;
            array.set( secondToSwap, temp ) ;


        }

    }




}
