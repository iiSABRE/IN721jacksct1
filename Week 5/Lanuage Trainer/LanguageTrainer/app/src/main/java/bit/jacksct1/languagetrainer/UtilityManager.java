package bit.jacksct1.languagetrainer;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by jacksct1 on 28/03/2016.
 */

//Manager class hadling the sorting of an array
public class UtilityManager
{



    public void sortArray(ArrayList array)
    {

        //Run 200 time to get a distributed shuffle
        for (int i = 0;i<200;i++)
        {
            //Pick two random idexes from array and making sure theyre not the same
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

            //swapping the two objects at the random indexes
            Object temp = array.set( firstToSwap, array.get( secondToSwap ) ) ;
            array.set( secondToSwap, temp ) ;


        }

    }




}
